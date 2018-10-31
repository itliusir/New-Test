package com.itliusir.test.jdk8.lambda.execute;

import com.itliusir.test.jdk8.lambda.finterface.FirstInterface;

/**
 * TestFirst
 *
 * @author liugang
 * @since 2018-05-04
 */
public class TestFirst {

    public static void main(String[] args) throws Exception {
        /*FirstInterface past = new FirstInterface(){
            @Override
            public String execute() throws Exception {
                return "past writing";
            }
        };
        System.out.println(past.execute());*/
        //new writing
        String a = test(() -> "new writing");
        System.out.println(a);
    }

    /**
     * 传递行为
     *
     * @author liugang
     * */
    private static String test(FirstInterface f) throws Exception {
        return f.execute();
    }
}
