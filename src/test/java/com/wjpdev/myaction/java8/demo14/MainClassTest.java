package com.wjpdev.myaction.java8.demo14;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;

import static org.junit.Assert.*;

public class MainClassTest {

    @Test
    public void fun1Test(){
        LocalDate date = LocalDate.of(2018, 06, 06);
        System.out.println(date.getYear() + "-" + date.getMonth() + "-" + date.getDayOfMonth());

        LocalDate today = LocalDate.now();
        System.out.println(today);
    }

    @Test
    public void fun2Test(){
        LocalTime now = LocalTime.now();
        System.out.println(now);
    }

    @Test
    public void fun3Test(){
        LocalDateTime current = LocalDateTime.now();
        System.out.println(current);
    }

    @Test
    public void fun4Test(){
        System.out.println(Instant.now().toString());
    }

    @Test
    public void fun5Test(){
        Period tenDays = Period.between(LocalDate.of(2018,06,06),
                LocalDate.of(2018, 06, 16));
        System.out.println(tenDays);
    }

    @Test
    public void fun6Test(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        date = date.plusYears(2).minusDays(10);
        date.withYear(2011);

        System.out.println(date);
    }

}