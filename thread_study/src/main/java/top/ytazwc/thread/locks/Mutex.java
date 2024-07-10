package top.ytazwc.thread.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author 花木凋零成兰
 * @title Mutex
 * @date 2024/7/10 11:09
 * @package top.ytazwc.thread.locks
 * @description 使用示例:Mutex独占锁的实现
 * 通过一个示例,直观感受,定义一个自定义同步组件的方式。下面的代码是一个自定义独占锁Mutex的实
 * 现方式,它同一时刻只允许一个线程占用。
 */
public class Mutex implements Lock {

    // 静态内部类 - 自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        // 是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // 当状态为0时获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁 状态设置为 0
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            // 缺少判断 - 存在释放别的线程的锁的问题
            setExclusiveOwnerThread(null);
            setState(0);
            return false;
        }

        // 返回一个Condition 每个Condition都包含一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    // 将操作代理到 Sync 上即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }
}
