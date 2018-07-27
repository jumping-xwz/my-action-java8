package com.wjpdev.myaction.java8.demo7;

import org.junit.Test;

import java.util.stream.Stream;

import static com.wjpdev.myaction.java8.demo7.MainClass.partitionPrimesWithCustomCollector;
import static org.junit.Assert.*;

public class MainClassTest {

    @Test
    public void fun1Test(){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        System.out.println(
                "Fastest execution done in " + fastest + " msecs");
    }

    @Test
    public void fun2Test(){
        long sum_val = Stream.iterate(1L, i->i+1)
                .limit(10)
                .reduce(0L, Long::sum);

        System.out.println(sum_val);
    }

    @Test
    public void fun3Test(){
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
    }
}