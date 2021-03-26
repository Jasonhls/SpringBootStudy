package com.cn.finalProperty;

import lombok.Data;

/**
 * @description:
 * @author: helisen
 * @create: 2021-03-26 10:04
 **/
@Data
public class Binder {
    private final String name;
    private final Integer age;

    public Binder(String name) {
        this(name, null);
    }

    public Binder(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void test() {
        A a = new A();
        System.out.println(a.toString());
    }

    final class A {
        public A() {
        }
    }

    @Override
    public String toString() {
        return "Binder{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
