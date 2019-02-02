package com.itliusir.test.gc;

import lombok.extern.slf4j.Slf4j;

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
    private static long[] BITMAP_TBL = new long[16];

    public static void main(String[] args) {
        TestMark testMark = new TestMark();
        ChildObj childObj = new ChildObj();
        childObj.setObjAddress(8L);
        childObj.setObj("我是爸爸");
        ChildObj childrenObj = new ChildObj();
        childrenObj.setObjAddress(14L);
        childrenObj.setObj("我是儿子");
        childObj.setChildrenObj(childrenObj);

        testMark.mark(childObj);
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
                log.info("BITMAP_TBL------------>{}", BITMAP_TBL);
                mark(children(obj));
            }
        }
    }

    private ChildObj children(IObject obj) {
        return ((ChildObj) obj).getChildrenObj();
    }
}
