package com.itliusir.test.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


/**
 * TODO
 *
 * @author liugang
 * @since 2018/12/3
 */
@Slf4j
public class TestExecutors {

    private static final int COUNT_BITS = Integer.SIZE - 3;

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
        log.info("ctl : {}", RUNNING | 1);
        /*
        * 使用Executors 创建线程池
        * */
        /*ExecutorService executorService = Executors.newFixedThreadPool(2, new TestThreadFactory());
        executorService.execute(new TestThread());
        executorService.execute(new TestThread1());
        executorService.execute(new TestThread2());

        ExecutorService cachedExecutorService = Executors.newCachedThreadPool(new TestThreadFactory());
        cachedExecutorService.execute(new TestThread());
        cachedExecutorService.execute(new TestThread1());
        cachedExecutorService.execute(new TestThread2());*/
        /*
        * 手动创建线程池
        * */
        PausableThreadPoolExecutor manualExecutorService = new PausableThreadPoolExecutor(0, 1, 500,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1), new TestThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        Future<?> submit = manualExecutorService.submit(new TestThread(), "success");
        Future<?> submit1 = manualExecutorService.submit(new TestThread1(), "success1");
        Future<?> submit2 = manualExecutorService.submit(new TestThread2(), "success2");
        manualExecutorService.pause();
        Thread.sleep(3000);
        manualExecutorService.resume();
        log.info(String.valueOf(submit.get()));
        log.info(String.valueOf(submit1.get()));
        log.info(String.valueOf(submit2.get()));
        // success fail exception cancel all return true

//        log.info((String) submit.get(4,TimeUnit.SECONDS));
        /*manualExecutorService.execute(new TestThread());
        manualExecutorService.execute(new TestThread1());
        manualExecutorService.execute(new TestThread2());*/
    }
}
