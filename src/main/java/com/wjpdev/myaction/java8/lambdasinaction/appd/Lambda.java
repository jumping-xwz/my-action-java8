package com.wjpdev.myaction.java8.lambdasinaction.appd;

import java.util.function.Function;

public class Lambda {
    Function<Object, String> f = obj -> obj.toString();
}
