package com.itliusir.test.gc;

/**
 * Class file 结构
 *
 * @author liugang
 * @since 2019/2/26
 */
public class ClassFileDemo {

    public static final boolean FLAG = true;
    public static final byte BYTE = 123;
    public static final char X = 'X';
    public static final short SHORT = 12345;
    public static final int INT = 123456789;
    public static final long LONG = 12345678901L;
    public static final float PI = 3.14f;
    public static final double E = 2.71828;

    public static void main(String[] args) throws RuntimeException {
        System.out.println("Hello, World!");
        circumference(1.6f);
    }

    /**
     * 00  ldc #4     3.14f -> stack top
     * 02  fstore_1   pop 3.14f -> #1 local var
     * 03  fconst_2   2f -> stack top
     * 04  fload_1    #1 local var -> stack top
     * 05  fmul       pop 2f and 3.14f -> mul -> stack top
     * 06  fload_0    #0 local var -> stack top
     * 07  fmul       pop 1.6f and 2*3.14f -> mul -> stack top
     * 08  fstore_2   pop 2*3.14*1.6f -> #2 local var
     * 09  fload_2    2*3.14*1.6f -> stack top
     * 10  return     pop 2*3.14*1.6f
     */
    public static float circumference(float r) {
        float pi = 3.14f;
        float area = 2 * pi * r;
        return area;
    }
}
