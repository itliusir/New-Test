package com.itliusir.test.jdk8.lambda.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-05-04
 */
@Data
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private Integer stuNum;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }
}
