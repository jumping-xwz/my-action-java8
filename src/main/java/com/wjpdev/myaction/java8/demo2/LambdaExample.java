package com.wjpdev.myaction.java8.demo2;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExample {

    public static void main(String[] args) {

        // Predicate<T>
        // T -> boolean
        // IntPredicate, LongPredicate, DoublePredicate
        Predicate<String> nonEmptyStringPredicate1 = (String s) -> !s.isEmpty();
        Predicate<String> nonEmptyStringPredicate2 = s -> !s.isEmpty();     // 类型推断

        System.out.println(nonEmptyStringPredicate1.test("abcd"));

        // Consumer<T>
        // T -> void
        // IntConsumer, LongConsumer, DoubleConsumer
        Consumer<Integer> printConsumer = (Integer i) -> System.out.println(i);
        printConsumer.accept(100);

        // Function<T,R>
        // T -> R
        // IntFunction<R>,
        // IntToDoubleFunction,
        // IntToLongFunction,
        // LongFunction<R>,
        // LongToDoubleFunction,
        // LongToIntFunction,
        // DoubleFunction<R>,
        // ToIntFunction<T>,
        // ToDoubleFunction<T>,
        // ToLongFunction<T>
        Function<Integer, String> applyFunc = (Integer origin) -> "String val:" + String.valueOf(origin);
        System.out.println(applyFunc.apply(200));

        Function<String, Demo2> applyDemo2 = Demo2::new;
        Demo2 demo2_1 = applyDemo2.apply("my name is demo2");
        System.out.println(demo2_1.getName());

        // Supplier<T>
        // () -> T
        //BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier
        Supplier<Demo2> demo2Supplier = Demo2::new;
        Demo2 demo2_2 = demo2Supplier.get();

        // UnaryOperator<T>
        // T -> T
        // IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator

        // BinaryOperator<T>
        // (T, T) -> T
        // IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator

        // BiPredicate<L,R>
        // (L, R) -> boolean

        // BiConsumer<T,U>
        // (T, U) -> void
        // ObjIntConsumer<T>, ObjLongConsumer<T>, ObjDoubleConsumer<T>

        // BiFunction<T, U, R>
        // (T, U) -> R
        // ToIntBiFunction<T,U>, ToLongBiFunction<T,U>, ToDoubleBiFunction<T,U>
        BiFunction<String, String, Demo2> demo2BiFunction = Demo2::new;
        Demo2 demo2_3 = demo2BiFunction.apply("demo name", "demo sex");

        // Custom
        // TriFunction
        // (T, U, V) -> R
        TriFunction<String, String, Integer, Demo2> demo2TriFunction = Demo2::new;
        Demo2 demo2_4 = demo2TriFunction.apply("demo name","demo sex",100);


        Comparator<Demo2> c = Comparator.comparing(Demo2::getName);

        // inventory.sort(comparing(Apple::getWeight));
        // inventory.sort(comparing(Apple::getWeight).reversed()); //逆序
        // inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));



    }
}
