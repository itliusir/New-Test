package com.itliusir.test.teststatic;

/**
 * TODO
 *
 * @author liugang
 * @since 2018/9/7
 */
public class HelloB extends HelloA{
    public HelloB(){
        System.out.println("H B");
    }
    {
        System.out.println("Iam B");
    }
    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        /**
         * static A
         * static B
         * Iam A
         * H A
         * Iam B
         * H B
         *
         */
        new HelloB();
    }
}
class HelloA{
    public HelloA(){
        System.out.println("H A");
    }
    {
        System.out.println("Iam A");
    }
    static {
        System.out.println("static A");
    }
}

