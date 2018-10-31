package com.itliusir.test.jdk8.lambda.execute;

import com.itliusir.test.jdk8.lambda.domain.Student;
import com.itliusir.test.jdk8.lambda.domain.User;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-05-04
 */
public class TestThree {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setName("tomcat");
        user.setAge(18);
        Student student = new Student();
        /*Function<User,Student> function = (user1 -> {
            BeanUtils.copyProperties(user1,student);
            student.setStuNum(1);
            return student;
        });
        Student student1 = function.apply(user);*/
        Student student1 = test(user,(user1 -> {
            BeanUtils.copyProperties(user1,student);
            student.setStuNum(1);
            return student;
        }));
        System.out.println(student1);
    }

    private static Student test(User user,Function<User,Student> f){
        return f.apply(user);
    }
}
