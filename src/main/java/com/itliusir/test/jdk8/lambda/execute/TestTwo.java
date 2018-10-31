package com.itliusir.test.jdk8.lambda.execute;

import com.itliusir.test.jdk8.lambda.domain.User;
import com.itliusir.test.jdk8.lambda.finterface.TwoInterface;

/**
 * 匿名类与lambda
 *
 * @author liugang
 * @since 2018-05-21
 */
public class TestTwo {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setAge(17);
        user.setName("外部对象");
        //inner class
        User iUser = innerTest(user);
        System.out.println(iUser);

        //lambda
        User lUser = lambdaTest(()-> userToNewUser(user,"lambda"));
        System.out.println(lUser);
    }

    private static User innerTest(User user){
        TwoInterface iTwo = new TwoInterface() {
            @Override
            public User convert() {
                return userToNewUser(user,"inner Class");
            };
        };
        return iTwo.convert();
    }

    private static User lambdaTest(TwoInterface twoInterface){
        return twoInterface.convert();
    }

    private static User userToNewUser(User user,String name){
        if(user.getId() == 1){
            User user1 = new User();
            user1.setId(2);
            user1.setAge(18);
            user1.setName(name);
            return user1;
        }
        return user;
    }
}
