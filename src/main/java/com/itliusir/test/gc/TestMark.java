package com.itliusir.test.gc;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 位图标记 Case
 *
 * @author liugang
 * @since 2019/2/2
 */

@Slf4j
public class TestMark {

    private static final long WORD_LENGTH = 1L;
    private static final long HEAP_START = 0L;
    private static final long HEAP_END = 15L;
    private static final long HEAP_SIZE = HEAP_END - HEAP_START + 1L;
    private static long[] BITMAP_TBL = new long[16];

    public static void main(String[] args) {
        TestMark testMark = new TestMark();
        ChildObj one = new ChildObj();
        one.setObjAddress(0L);
        one.setObj("我是爷爷");
        ChildObj two = new ChildObj();
        two.setObjAddress(4L);
        two.setObj("我是爸爸");
        ChildObj three = new ChildObj();
        three.setObjAddress(6L);
        three.setObj("我是儿子");
        two.setChildrenObj(three);
        one.setChildrenObj(two);
        // 位图标记
        testMark.mark(one);

        // 位图清理
        testMark.sweepAndPhase();
    }

    /**
     * 位图标记方法
     *
     * @param obj 堆中对象
     * @author liugang 2019/2/2 16:47
     */
    private void mark(IObject obj) {
        if (obj != null) {
            long objNum = (obj.getAddress() - HEAP_START) / WORD_LENGTH;
            int index = (int) (objNum / WORD_LENGTH);
            int offset = (int) (objNum % WORD_LENGTH);
            // 如果相对应位都是1，则结果为1，否则为0
            if ((BITMAP_TBL[index] & (1 << offset)) == 0) {
                // 如果相对应位都是0，则结果为0，否则为1
                BITMAP_TBL[index] |= (1 << offset);
                log.info("Marking--BITMAP_TBL------------>{}", BITMAP_TBL);
                mark(children(obj));
            }
        }
    }

    /**
     * 这里模拟 -1 代表清空
     *
     * @author liugang 2019/2/3 11:20
     */
    private void sweepAndPhase() {
        long sweeping = HEAP_START;
        int index = 0;
        int offset = 0;
        List<Integer> freeList = new LinkedList<>();
        while (sweeping < HEAP_SIZE) {
            if ((BITMAP_TBL[index] & (1 << offset)) == 0) {
                freeList.add((int) sweeping);
            }
            index += (offset + 1) / WORD_LENGTH;
            offset = (int) ((offset + 1) % WORD_LENGTH);
            sweeping++;
        }
        for (int freeIndex : freeList) {
            BITMAP_TBL[freeIndex] = -1;
        }
        log.info("Sweeping--BITMAP_TBL------------>{}", BITMAP_TBL);
    }

    private ChildObj children(IObject obj) {
        return ((ChildObj) obj).getChildrenObj();
    }
}
