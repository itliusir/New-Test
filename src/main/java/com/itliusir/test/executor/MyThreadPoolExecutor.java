package com.itliusir.test.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 线程池
 *
 * @author liugang
 * @since 2018/12/3
 */
@Slf4j
public class MyThreadPoolExecutor extends AbstractExecutorService {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    // 32
    // 0010 0000
    // 0001 1101

    /**
     * ‭0001 1111 1111 1111 1111 1111 1111 1111‬
     */
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
    /**
     * 1110 0000 0000 0000 0000 0000 0000 0000
     */
    private static final int RUNNING = -1 << COUNT_BITS;
    /**
     * 0
     */
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    /**
     * 0010 0000 0000 0000 0000 0000 0000 0000
     */
    private static final int STOP = 1 << COUNT_BITS;
    /**
     * 0100 0000 0000 0000 0000 0000 0000 0000
     */
    private static final int TIDYING = 2 << COUNT_BITS;
    /**
     * 0110 0000 0000 0000 0000 0000 0000 0000
     */
    private static final int TERMINATED = 3 << COUNT_BITS;

    static AtomicInteger ctl = new AtomicInteger(RUNNING | 0);

    private final BlockingQueue<Runnable> workQueue;

    private final ReentrantLock mainLock = new ReentrantLock();

    private final Condition termination = mainLock.newCondition();

    private final HashSet<Worker> workers = new HashSet<Worker>();

    private volatile RejectedExecutionHandler handler;

    private volatile int corePoolSize;

    private volatile int maximumPoolSize;

    private static final RejectedExecutionHandler defaultHandler =
            new ThreadPoolExecutor.AbortPolicy();

    private final RuntimePermission shutdownPerm =
            new RuntimePermission("modifyThread");

    private int largestPoolSize;

    private volatile boolean allowCoreThreadTimeOut;

    private volatile long keepAliveTime;

    private volatile ThreadFactory threadFactory;

    private long completedTaskCount;

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        log.info("capacity : {}", String.valueOf(CAPACITY));
        log.info("~capacity : {}", String.valueOf(~CAPACITY));
        log.info("running : {}", String.valueOf(RUNNING));
        log.info("shutdown : {}", String.valueOf(SHUTDOWN));
        log.info("stop : {}", String.valueOf(STOP));
        log.info("tidying : {}", String.valueOf(TIDYING));
        log.info("terminated : {}", String.valueOf(TERMINATED));
        /**
         * 1110 0000 0000 0000 0000 0000 0000 0001‬
         *
         * 高3位代表线程池状态 低29位表示线程池数量
         */
        log.info("ctl : {}", RUNNING | 0);

        MyThreadPoolExecutor testExecutors = new MyThreadPoolExecutor(2, 2, 0,
                TimeUnit.NANOSECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), defaultHandler);
        testExecutors.execute(
                () -> {
                    System.out.println("时间已过----1");
                }
        );
        testExecutors.execute(
                () -> {
                    System.out.println("时间已过----2");
                }
        );
        testExecutors.execute(
                () -> {
                    System.out.println("时间已过----3");
                }
        );

    }

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
                                TimeUnit unit, BlockingQueue<Runnable> workQueue,
                                ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    public void execute(Runnable command) {
        int c = ctl.get();
        // 计算当前线程池的线程数
        if ((c & CAPACITY) < corePoolSize) {
            // 如果小于corePoolSize则执行addWorker,方法结束
            if (addWorker(command, true)) {
                return;
            }
            c = ctl.get();
        }
        // 如果线程池数大于等于corePoolSize，则加入队列
        if (c < SHUTDOWN && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (!(recheck < SHUTDOWN) && remove(command)) {
                reject(command);
            } else if ((recheck & CAPACITY) == 0) {
                addWorker(null, false);
            }
        } else if (!addWorker(command, false)) {
            // 添加失败则执行拒绝策略
            reject(command);
        }
    }

    private void reject(Runnable command) {
        throw new RejectedExecutionException("Task " + command.toString() +
                " rejected from TestExecutors");
    }

    private boolean remove(Runnable command) {
        boolean removed = workQueue.remove(command);
        tryTerminate();
        return removed;
    }

    /**
     * 1. command, true : workerNum > coreSize --> return false
     * 2. command, false : workerNum > maxSize --> return false
     *
     * @param command task
     * @param core 是否核心线程
     *
     * @author liugang 2019/6/3 19:30
     * */
    private boolean addWorker(Runnable command, boolean core) {
        retry:
        for (; ; ) {
            int c = ctl.get();
            int rs = c & ~CAPACITY;
            if (rs >= SHUTDOWN &&
                    !(rs == SHUTDOWN &&
                            command == null &&
                            !workQueue.isEmpty()))
                return false;
            for (; ; ) {
                int wc = c & CAPACITY;
                // 如果不是核心线程(大于核心线程或最大线程)
                if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize)) {
                    return false;
                }
                // 线程数+1，然后跳出当前循环
                if (compareAndIncrementWorkerCount(c)) {
                    break retry;
                }
                c = ctl.get();
                if ((c & ~CAPACITY) != rs) {
                    continue retry;
                }
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            // 创建一个worker
            w = new Worker(command);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    int rs = ctl.get() & ~CAPACITY;
                    if (rs < SHUTDOWN || (rs == SHUTDOWN && command == null)) {
                        if (t.isAlive()) {
                            throw new IllegalThreadStateException();
                        }
                        // add -> set
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize) {
                            largestPoolSize = s;
                        }
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    // ->worker#run()->worker#runWorker
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted) {
                addWorkerFailed(w);
            }
        }
        return workerStarted;
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null) {
                workers.remove(w);
            }
            do {
            } while (!compareAndDecrementWorkerCount(ctl.get()));
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    final void tryTerminate() {
        for (; ; ) {
            int c = ctl.get();
            if (c < SHUTDOWN || c >= TIDYING || ((c & ~CAPACITY) == SHUTDOWN && !workQueue.isEmpty())) {
                return;
            }
            if ((c & CAPACITY) != 0) {
                interruptIdleWorkers(true);
                return;
            }

            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                if (ctl.compareAndSet(c, TIDYING | 0)) {
                    try {
                        terminated();
                    } finally {
                        ctl.set(TERMINATED | 0);
                        termination.signalAll();
                    }
                }
            } finally {
                mainLock.unlock();
            }
        }
    }

    private void terminated() {
    }

    private void interruptIdleWorkers(boolean onlyOne) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (Worker worker : workers) {
                Thread t = worker.thread;
                if (!t.isInterrupted() && worker.tryLock()) {
                    try {
                        t.interrupt();
                    } catch (SecurityException ignore) {
                    } finally {
                        worker.unlock();
                    }
                }
                if (onlyOne) {
                    break;
                }
            }
        } finally {
            mainLock.unlock();
        }
    }

    private boolean compareAndIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    private boolean compareAndDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    @Override
    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        checkShutdownAccess();
        advanceRunState(SHUTDOWN);
        interruptIdleWorkers(false);
    }

    private void advanceRunState(int shutdown) {
        for (; ; ) {
            int c = ctl.get();
            if (c >= shutdown || ctl.compareAndSet(c, shutdown | (c & CAPACITY))) {
                break;
            }
        }
    }

    private void checkShutdownAccess() {
        SecurityManager securityManager = new SecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(shutdownPerm);
            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                for (Worker w : workers) {
                    securityManager.checkAccess(w.thread);
                }
            } finally {
                mainLock.unlock();
            }
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        List<Runnable> tasks;
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            checkShutdownAccess();
            advanceRunState(STOP);
            interruptWorkers();
            tasks = drainQueue();
        } finally {
            mainLock.unlock();
        }
        tryTerminate();
        return tasks;
    }

    private List<Runnable> drainQueue() {
        BlockingQueue<Runnable> q = workQueue;
        ArrayList<Runnable> taskList = new ArrayList<>();
        q.drainTo(taskList);
        if (!q.isEmpty()) {
            for (Runnable r : q.toArray(new Runnable[0])) {
                if (q.remove(r)) {
                    taskList.add(r);
                }
            }
        }
        return taskList;
    }

    private void interruptWorkers() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (Worker w : workers) {
                w.interruptIfStarted();
            }
        } finally {
            mainLock.unlock();
        }
    }

    @Override
    public boolean isShutdown() {
        return !(ctl.get() < SHUTDOWN);
    }

    @Override
    public boolean isTerminated() {
        int c = ctl.get();
        return c >= TERMINATED;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (; ; ) {
                if (ctl.get() >= TERMINATED) {
                    return true;
                }
                if (nanos <= 0) {
                    return false;
                }
                nanos = termination.awaitNanos(nanos);
            }
        } finally {
            mainLock.unlock();
        }
    }

    private final class Worker
            extends AbstractQueuedSynchronizer
            implements Runnable {
        /**
         * This class will never be serialized, but we provide a
         * serialVersionUID to suppress a javac warning.
         */
        private static final long serialVersionUID = 6138294804551838833L;

        /**
         * Thread this worker is running in.  Null if factory fails.
         */
        final Thread thread;
        /**
         * Initial task to run.  Possibly null.
         */
        Runnable firstTask;
        /**
         * Per-thread task counter
         */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         *
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        /**
         * Delegates main run loop to outer runWorker
         */
        public void run() {
            runWorker(this);
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() {
            acquire(1);
        }

        public boolean tryLock() {
            return tryAcquire(1);
        }

        public void unlock() {
            release(1);
        }

        public boolean isLocked() {
            return isHeldExclusively();
        }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }

    private void runWorker(Worker worker) {
        Thread wt = Thread.currentThread();
        Runnable task = worker.firstTask;
        worker.firstTask = null;
        worker.unlock();
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                worker.lock();
                if (((ctl.get() >= STOP) || (Thread.interrupted() && ctl.get() >= STOP)) && !wt.isInterrupted()) {
                    wt.interrupt();
                }
                try {
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        task.run();
                    } catch (RuntimeException x) {
                        thrown = x;
                        throw x;
                    } catch (Error x) {
                        thrown = x;
                        throw x;
                    } catch (Throwable x) {
                        thrown = x;
                        throw x;
                    } finally {
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    worker.completedTasks++;
                    worker.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(worker, completedAbruptly);
        }
    }

    private void processWorkerExit(Worker worker, boolean completedAbruptly) {
        if (completedAbruptly) {
            do {
            } while (!compareAndDecrementWorkerCount(ctl.get()));
        }

        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            completedTaskCount += worker.completedTasks;
            workers.remove(worker);
        } finally {
            mainLock.unlock();
        }

        tryTerminate();

        int c = ctl.get();
        if (c < STOP) {
            if (!completedAbruptly) {
                int min = allowCoreThreadTimeOut ? 0 : corePoolSize;
                if (min == 0 && !workQueue.isEmpty()) {
                    min = 1;
                }
                if ((c & CAPACITY) >= min) {
                    return;
                }
            }
            addWorker(null, false);
        }


    }

    private void afterExecute(Runnable task, Throwable thrown) {
    }

    private void beforeExecute(Thread wt, Runnable task) {
    }

    private Runnable getTask() {
        boolean timeOut = false;

        for (; ; ) {
            int c = ctl.get();
            int rs = c & ~CAPACITY;

            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                do {
                } while (!compareAndDecrementWorkerCount(ctl.get()));
                return null;
            }

            int wc = c & CAPACITY;
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;

            if ((wc > maximumPoolSize || (timed && timeOut)) && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c)) {
                    return null;
                }
                continue;
            }

            try {
                Runnable r = timed ? workQueue.poll(0, TimeUnit.NANOSECONDS) : workQueue.take();
                if (r != null) {
                    return r;
                }
                timeOut = true;
            } catch (InterruptedException e) {
                timeOut = false;
            }
        }
    }

}
