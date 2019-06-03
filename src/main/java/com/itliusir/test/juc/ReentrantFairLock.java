package com.itliusir.test.juc;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 公平锁
 *
 * @author liugang
 * @since 2019/4/28
 */

@Slf4j
public class ReentrantFairLock implements Lock,Serializable {

    private static final long serialVersionUID = -39341565663961849L;

    private final Sync sync;

    abstract static class Sync extends AbstractQueuedSynchronizer {

        private static final long serialVersionUID = -5093288313087542939L;

        abstract void lock();

        /**
         * 公平获取锁
         *
         * @author liugang 2019/4/29
         * */
        final boolean fairTryAcquire(int acquires) {
            Thread currentThread = Thread.currentThread();
            int state = getState();
            log.info("try acquire locking....state:{}",state);
            // 当前没有线程获取到锁
            if (state == 0) {
                // AQS queue里无等待线程(保证公平)则通过CAS获取锁
                if (!hasQueuedPredecessors() && compareAndSetState(0,acquires)) {
                    log.info("state is zero, and not has queued predecessors.. will set the exclusive lock");
                    setExclusiveOwnerThread(currentThread);
                    return true;
                }
            // 获取锁的还是当前线程，重入自增
            } else if (isHeldExclusively()) {
                int nextState = acquires + state;
                if (nextState < 0) {
                    throw new Error("超出最大状态值");
                }
                log.info("exclusive thread is current thread,nextState is {}",nextState);
                setState(nextState);
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         *
         * @author liugang 2019/4/29
         * */
        @Override
        protected final boolean tryRelease(int releases) {
            // 释放
            int state = getState() - releases;
            log.info("try release locking....state:{}",state);
            // 是否为获取锁的线程
            if (!isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            // 锁全部释放
            if (state == 0) {
                free = true;
                // 清空独占线程
                log.info("will empty the exclusive thread");
                setExclusiveOwnerThread(null);
            }
            setState(state);
            return free;
        }

        /**
         * 当前线程是否为获取到锁的线程
         *
         * @author liugang 2019/4/29
         * */
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

    }


    class FairLock extends Sync {

        private static final long serialVersionUID = 4099933098073812424L;

        @Override
        final void lock() {
            acquire(1);
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            return fairTryAcquire(acquires);
        }

    }

    public ReentrantFairLock() {
        this.sync = new FairLock();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.fairTryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
