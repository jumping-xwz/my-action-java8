package com.wjpdev.myaction.java8.demo2;

public class LambdaThread {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("hello world!")).run();
        new Thread(() -> System.out.println("hello Qia Qia!")).run();
        new Thread(() -> System.out.println("hello Gou Gou!")).run();
    }
}
