package com.itliusir.test.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 原子累加
 *
 * @author liugang
 * @since 2018/12/6
 */

@Slf4j
public class TestIntegerByCell implements Serializable {

    private static Unsafe unsafe;
    private static long iOffset;
    private static final long serialVersionUID = 5363964268000353742L;
    private LongAdder longAdder = new LongAdder();

    private int i = 0;

    static {
        try {
            Field field = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
            boolean orignialAccessible = field.isAccessible();
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            iOffset = unsafe.objectFieldOffset
                    (TestIntegerByCell.class.getDeclaredField("i"));
            field.setAccessible(orignialAccessible);
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void safeAdd() {
        longAdder.add(1);
    }

    private void add() {
        for(;;) {
            int value = i;
            if (unsafe.compareAndSwapInt(this,iOffset,value,value + 1)) {
                break;
            }
        }
    }

    @Test
    public void countTest() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int k = 0; k < 1000; k++) {
            es.execute(() -> {
                safeAdd();
                add();
            });
        }

        Thread.sleep(5000);

        System.out.println(longAdder.intValue());
        System.out.println(i);

    }


}
