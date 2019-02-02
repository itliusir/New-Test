package com.itliusir.test.gc;

import lombok.Data;

/**
 * 模拟堆中对象
 *
 * @author liugang
 * @since 2019/2/2
 */

@Data
public class ChildObj implements IObject{

    /**
     * 对象头
     */
    private Long objHeader;

    /**
     * 对象地址
     */
    private Long objAddress;

    /**
     * 对象Data
     */
    private String obj;

    /**
     * 引用别的对象
     */
    private ChildObj childrenObj;

    @Override
    public long getAddress() {
        return objAddress;
    }
}
