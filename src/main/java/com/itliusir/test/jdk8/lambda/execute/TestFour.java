package com.itliusir.test.jdk8.lambda.execute;

import java.util.function.Predicate;

/**
 * 使用Predicate
 *
 * @author liugang
 * @since 2018-05-04
 */
public class TestFour {

    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void main(String[] args) {
        //one
        Predicate<String> p = (String password) -> "123456".equals(password);
        /*new Predicate<String>(){
            @Override
            public boolean test(String password) {
                return "123456".equals(password);
            }
        };*/
        System.out.println(p.test("123456")==true?"success":"fail");

        //two
        System.out.println(test("123456",a -> "123456".equals(a)));

    }

    private static String test(String password,Predicate<String> p) {
        if(p.test(password)){
            return "success";
        }
        return "fail";
    }
}
