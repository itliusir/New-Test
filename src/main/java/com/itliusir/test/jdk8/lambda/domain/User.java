package com.itliusir.test.jdk8.lambda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-05-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }
}
