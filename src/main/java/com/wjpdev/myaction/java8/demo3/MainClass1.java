package com.wjpdev.myaction.java8.demo3;

import java.util.function.DoubleFunction;
import java.util.function.Function;

public class MainClass1 {

    public static void main(String[] args) {

        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);

        System.out.println(transformationPipeline.apply("wjpdeveloper"));

        //求 f(x+10)dx , [3,7] 的梯形阴影面积
        System.out.println(integrate(x -> x + 10, 3, 7));
    }

    public static double integrate(DoubleFunction<Double> f, double a, double b){
        return (f.apply(a) + f.apply(b) * (b - a) / 2.0);
    }
}
