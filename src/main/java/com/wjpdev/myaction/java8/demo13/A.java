package com.wjpdev.myaction.java8.demo13;

public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
