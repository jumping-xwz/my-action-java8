package com.wjpdev.myaction.java8.demo7;

import com.wjpdev.myaction.java8.demo4.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class MainClass {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> datas = partitionPrimesWithCustomCollector(10);

        datas.get(Boolean.TRUE).stream().forEach(System.out::println);

        System.out.println("--------------");

        datas.get(Boolean.FALSE).stream().forEach(System.out::println);


    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }
}
