package com.wjpdev.myaction.java8.demo15;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class MainClassTest {

    @Test
    public void fun1Test(){
        List<String> collected = Stream.of("a", "b", "c")
                .collect(toList());

        assertEquals(asList("a", "b", "c"), collected);
    }

    @Test
    public void fun2Test(){
        List<String> collected = new ArrayList<>();
        for (String string : asList("a", "b", "hello")) {
            String uppercaseString = string.toUpperCase();
            collected.add(uppercaseString);
        }

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void fun3Test(){
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());

        assertEquals(asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void fun4Test(){
        List<Integer> together = Stream.of(asList(1, 2), asList(3, 4))
                .flatMap(numbers -> numbers.stream())
                .collect(toList());

        assertEquals(asList(1, 2, 3, 4), together);
    }

    @Test
    public void fun5Test(){
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);

        assertEquals(6, count);
    }
}