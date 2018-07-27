package com.wjpdev.myaction.java8.demo5;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MainClass1Test {

    private static List<Transaction> transactions;

    @BeforeClass
    public static void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void fun1Test() {
        transactions.stream().forEach(System.out::println);
    }

    /**
     * 找出2011年的所有交易并按交易额排序（从低到高）
     */
    @Test
    public void fun2Test() {
        List<Transaction> tr2011 =
                transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        tr2011.stream().forEach(System.out::println);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void fun3Test() {
        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(toList());
        cities.stream().forEach(System.out::println);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void fun4Test() {
        Set<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        cities.stream().forEach(System.out::println);
    }

    /**
     * 查找所有来自于剑桥的交易员，并按姓名排序
     */
    @Test
    public void fun5Test() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        traders.stream().forEach(System.out::println);
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void fun6Test() {
        Optional<String> traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce((n1, n2) -> n1 + "," + n2);
        traderStr.ifPresent(System.out::println);
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序
     */
    @Test
    public void fun7Test() {
        String traderStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(","));
        System.out.println(traderStr);
    }

    /**
     * 有没有交易员是在米兰工作的
     */
    @Test
    public void fun8Test() {
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(milanBased);
    }

    /**
     * 打印生活在剑桥的交易员的所有交易额
     */
    @Test
    public void fun9Test(){
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * 所有交易中，最高的交易额是多少
     */
    @Test
    public void fun10Test(){
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        highestValue.ifPresent(System.out::println);
    }

    /**
     * 找到交易额最小的交易
     */
    @Test
    public void fun11Test(){
        Optional<Transaction> smallestTransaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
        smallestTransaction.ifPresent(System.out::println);
    }

    /**
     * 找到交易额最小的交易
     */
    @Test
    public void fun12Test(){
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
        smallestTransaction.ifPresent(System.out::println);
    }
}