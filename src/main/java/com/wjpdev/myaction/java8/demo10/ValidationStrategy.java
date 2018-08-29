package com.wjpdev.myaction.java8.demo10;

@FunctionalInterface
public interface ValidationStrategy {
    boolean execute(String s);
}
