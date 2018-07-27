package com.wjpdev.myaction.java8.demo6;

import org.junit.Test;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainClassTest {

    @Test
    public void fun1Test(){
        Stream<int []> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                    IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
                );

        pythagoreanTriples.limit(5)
                .forEach( t ->
                        System.out.println(t[0] + "," + t[1] + "," + t[2]));

    }

    @Test
    public void fun2Test(){
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 斐波纳契元组序列
     */
    @Test
    public void fun3Test(){
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }

    /**
     * 斐波纳契数列
     */
    @Test
    public void fun4Test(){
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .map(t -> t[0])
                .forEach(System.out::println);
    }

    @Test
    public void fun5Test(){
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void fun6Test(){
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);
    }

}