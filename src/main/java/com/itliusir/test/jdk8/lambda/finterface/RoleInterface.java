package com.itliusir.test.jdk8.lambda.finterface;

/**
 * TODO
 *
 * @author liugang
 * @since 2018-06-05
 */
@FunctionalInterface
public interface RoleInterface<T, R> {

    R haveRoles(T t);
}
