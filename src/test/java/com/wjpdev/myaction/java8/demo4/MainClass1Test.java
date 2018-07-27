package com.wjpdev.myaction.java8.demo4;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MainClass1Test {

    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    @Test
    public void fun1Test() {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }

    @Test
    public void fun2Test() {
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }

    @Test
    public void fun3Test() {
        List<Dish> vegetarianDishes =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());
        System.out.println(vegetarianDishes);
    }

    @Test
    public void fun4Test() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void fun5Test() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
    }

    @Test
    public void fun6Test() {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(uniqueCharacters);
    }

    @Test
    public void fun7Test(){
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .filter(j -> (i+j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        pairs.stream().forEach(item -> System.out.println("(" + item[0] + "," + item[1]+")"));
    }

    @Test
    public void fun8Test(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        firstSquareDivisibleByThree.ifPresent(System.out::println);
    }

    @Test
    public void fun9Test(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }

    @Test
    public void fun10Test(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = someNumbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
    }

    @Test
    public void fun11Test(){
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
//        int sum = someNumbers.stream().reduce(0, Integer::max);
        Optional<Integer> sum = someNumbers.stream().reduce(Integer::max);
        sum.ifPresent(System.out::println);
    }
}