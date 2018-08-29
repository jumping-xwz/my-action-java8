package com.wjpdev.myaction.java8.demo9;

import static com.wjpdev.myaction.java8.demo9.Task.doSomething;

public class MainClass {

    public static void main(String[] args) {

        doSomething((Runnable) () -> System.out.println("Runnable Danger danger!!"));

        doSomething((Task) () -> System.out.println("Task Danger danger!!"));
    }
}
