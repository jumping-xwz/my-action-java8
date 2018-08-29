package com.wjpdev.myaction.java8.demo9;

public interface Task {
    public void execute();

    public static void doSomething(Runnable r){ r.run(); }
    public static void doSomething(Task a){ a.execute(); }
}
