package com.wjpdev.myaction.java8.demo2;

public class Demo2 {
    private String name;
    private String sex;
    private Integer height;

    public Demo2() {
    }

    public Demo2(String name) {
        this.name = name;
    }

    public Demo2(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    public Demo2(String name, String sex, int height) {
        this.name = name;
        this.sex = sex;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
