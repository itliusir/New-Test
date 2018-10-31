package com.itliusir.test.jvm;

/**
 * 0: aload_0
 * 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 * 4: return
 *
 * @author liugang
 * @since 2018-04-28
 */
public class Example1 {
    /**
     * 为主方法创建一个frame并将其推入线程栈
     * 用于执行方法的是method frame，该frame由两个主要部分组成:
     *  1. local variables Array 局部变量数组--存储方法参数和局部变量位置
     *  2. Operand Stack        操作数栈--执行方法的计算
     * */
    public static void main(String[] args) {
         /*
          * Code:
          * 0: iconst_2
          * 1: iconst_3
          * 2: invokestatic  #2                  // Method add:(II)I
          * 5: istore_1
          * 6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
          * 9: iload_1
          * 10: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
          * 13: return
          *
          * 步骤：
          * local variables: args(0) result(1)
          * 2 3放入操作数栈中
          * invoke static add a=2 b=3
          * add(int a, int b);
          * add GG,main的操作数栈保存add的return值:5
          * istore_1 将其弹出并将其设置为索引为1(variable result)的变量的值
          * */
        int result = add(2,3);
        /*
         *  get static将java/lang/System.out压入操作数栈
         *  现在操作数栈里有两个值 out和5
         *  invoke virtual 调用PrintStream.println方法
         *  它从操作数栈中弹出两个元素：
             *  第一个元素是一个要传递给println方法的整数参数
             *  第二个元素是对将要调用println方法的对象的引用
         * */
        System.out.println(result);
    }

    /**
     * iload 将局部变量表的0 1位置int型变量2 3加载到栈顶
     * iadd从栈顶弹出两个数 相加 把和送入到栈顶
     * 最后，ireturn弹出顶层元素
     *
     * 0: iload_0
     * 1: iload_1
     * 2: iadd
     * 3: ireturn
     *
     * @author liugang 2018-04-28 10:47
     * */
    public static int add(int a, int b) {
        return a+b;
    }
}
