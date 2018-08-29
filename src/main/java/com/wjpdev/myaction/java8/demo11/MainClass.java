package com.wjpdev.myaction.java8.demo11;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class MainClass {
    public static void main(String[] args) {
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result = pipeline.apply("Aren't labdas really sexy?!!");

        System.out.println(result);
    }
}
