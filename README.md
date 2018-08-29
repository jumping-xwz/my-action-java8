# my-action-java8

此处列出我关于 Java8 的学习实践。

- [Lambda in action](lambda-in-action.md) https://github.com/java8/Java8InAction.git

# HandBook

## JDK8 新增特性
- Lambda 表达式(匿名函数)
- 流处理(stream API) 
- 用行为参数化把代码传递给方法
- 方法引用
- 函数式编程
- 默认方法、新的Optional类、CompleteableFuture
- 新的日期和时间API

#### 1.
Java 8中的主要变化反映了它开始远离常侧重改变现有值的经典面向对象思想，而向函数式编程领域转变，在大面上考虑做什么（例如，创建一个值代表所有从A到B低于给定价格的交通线路）被认为是头等大事，并和如何实现（例如，扫描一个数据结构并修改某些元素）区分开来。

#### 2.
Scala和Groovy等语言的实践已经证明，让方法等概念作为一等值可以扩充程序员的工具库，从而让编程变得更容易。一旦程序员熟悉了这个强大的功能，他们就再也不愿意使用没有这一功能的语言了。因此，Java 8的设计者决定允许方法作为值，让编程更轻松。此外，让方法作为值也构成了其他若干Java 8功能（如 Stream ）的基础。

#### 3.
Collection主要是为了存储和访问数据，而Stream则主要用于描述对数据的计算。这里的关键点在于，Stream允许并提倡并行处理一个 Stream 中的元素。


#### 4.
Java 8中加入默认方法主要是为了支持库设计师，让他们能够写出更容易改进的接口。

#### 5. Lambda
![lambda](pics/Image-1.png)

**Lambda 表达式三要素**：
- 参数列表
- 箭头
- Lambda主体

```
(parameters) -> expression
```
or
```
(parameters) -> { statements; }
```

Q: 在哪里以及如何使用 Lambda

A: 可以在函数式接口上使用Lambda表达式。

**使用Lambda好处**：
- 有利于行为参数化
- 有条件的延迟更新
- 环绕执行

#### 6.
- 函数式接口就是只定义一个抽象方法的接口。
- 接口现在还可以拥有 默认方法（即在类没有对方法进行实现时，其主体为方法提供默认实现的方法）。哪怕有很多默认方法，只要接口只定义了一个抽象方法抽象方法，它就仍然是一个函数式接口。

用函数式接口可以干什么呢？Lambda表达式允许你直接以内联的形式为函数式接口的抽象方法提供实现，并把整个表达式作为函数式接口的实例。

#### 7.
```
@FunctionalInterface
```

函数描述符: 函数式接口的抽象方法的签名基本上就是Lambda表达式的签名。我们将这种抽象方法叫作函数描述符。

#### 8. 常用函数式接口

- `java.util.function.Predicate<T>`  定义了一个名叫 test 的抽象方法，它接受泛型 T 对象，并返回一个 boolean 。
- `java.util.function.Consumer<T>` 定义了一个名叫 accept 的抽象方法，它接受泛型 T 的对象，没有返回（ void ）。 
- `java.util.function.Function<T, R>` 定义了一个叫作 apply 的方法，它接受一个泛型 T 的对象，并返回一个泛型 R 的对象。


#### 9.
Lambda可以没有限制地捕获（也就是在其主体中引用）实例变量和静态变量。但局部变量必须显式声明为 final ，或事实上是 final 。

#### 10. 方法引用
方法引用可以被看作仅仅调用特定方法的Lambda的一种快捷写法。它的基本思想是，如果一个Lambda代表的只是“直接调用这个方法”，那最好还是用名称来调用它，而不是去描述如何调用它。事实上，方法引用就是让你根据已有的方法实现来创建Lambda表达式。但是，显式地指明方法的名称，你的代码的可读性会更好。它是如何工作的呢？当你需要使用方法引用时，目标引用放在分隔符 :: 前，方法的名称放在后面。

方法引用的等价表示：
```
(Apple a) -> a.getWeight()                Apple::getWeight
() -> Thread.currentThread().dumpStack()  Thread.currentThread()::dumpStack
(str, i) -> str.substring(i)              String::substring
(String s) -> System.out.println(s)       System.out::println
```
![lambda-method-ref](pics/Image-2.png)

- 构造函数引用 `ClassName::new`

#### 11. 流
流是Java API的新成员，它允许你以声明性方式处理数据集合。请注意，和迭代器类似，流只能遍历一次。你可以从原始数据源那里再获得一个新的流来重新遍历一遍，就像迭代器一样。

流的使用一般包括三件事：
- 一个数据源（如集合）来执行一个查询；
- 一个中间操作链，形成一条流的流水线；
  - filter
  - distinct
  - skip
  - limit
  - map
  - mapToInt
  - flatmap
  - sorted
- 一个终端操作，执行流水线，并能生成结果。
  - anyMatch
  - noneMatch
  - allMatch
  - findAny
  - findFirst
  - forEach
  - collect
  - reduce
  - count
  - sum
  - min
  - max
  
- IntStream
  - range
  - rangeClosed

**构建流**
- 由值创建流 
```
Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
Stream.empty()
````

- 由数组创建流
```
int[] numbers = {2, 3, 5, 7, 11, 13};
int sum = Arrays.stream(numbers).sum();
```

- 由文件生成流
```
long uniqueWords = 0;
try(Stream<String> lines =
    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
        uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
            .distinct()
            .count();
} catch(IOException e) {

}
```

- 由函数生成流
```
Stream.iterate(0, n -> n + 2)
    .limit(10)
    .forEach(System.out::println);
```

#### 12. 收集器
预定义收集器的功能，也就是那些可以从 Collectors
类提供的工厂方法（例如 groupingBy ）创建的收集器。它们主要提供了三大功能：
- 将流元素归约和汇总为一个值
- 元素分组
- 元素分区

**归约汇总**
```
Map<Currency, List<Transaction>> transactionsByCurrencies =
transactions.stream().collect(groupingBy(Transaction::getCurrency));

List<Transaction> transactions =
transactionStream.collect(Collectors.toList());

long howManyDishes = menu.stream().collect(Collectors.counting());
long howManyDishes = menu.stream().count();

Optional<Dish> mostCalorieDish =
    menu.stream()
        .collect(maxBy(dishCaloriesComparator));
        
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
double avgCalories =menu.stream().collect(averagingInt(Dish::getCalories));
IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));

String shortMenu = menu.stream().map(Dish::getName).collect(joining());
String shortMenu = menu.stream().collect(joining());

int totalCalories = menu.stream().collect(reducing(
        0, Dish::getCalories, (i, j) -> i + j));
int totalCalories = menu.stream().collect(reducing(0,
        Dish::getCalories,
        Integer::sum));
int totalCalories = menu.stream().
    map(Dish::getCalories).reduce(Integer::sum).get();
int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum(); 
```

**分组**
```
Map<Dish.Type, List<Dish>> dishesByType =
    menu.stream().collect(groupingBy(Dish::getType));
    
Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
    groupingBy(dish -> {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    })
);
```

**多级分组**
```
Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
    menu.stream().collect(
        groupingBy(Dish::getType,
            groupingBy(dish -> {
                if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                else return CaloricLevel.FAT;
            } )
        )
    );
```

**把收集器的结果转换为另一种类型**
```
Map<Dish.Type, Dish> mostCaloricByType =
    menu.stream()
    .collect(groupingBy(Dish::getType,
        collectingAndThen(
            maxBy(comparingInt(Dish::getCalories)),
            Optional::get)));
            
Map<Dish.Type, Integer> totalCaloriesByType =
    menu.stream().collect(groupingBy(Dish::getType,
        summingInt(Dish::getCalories)));
        
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
    menu.stream().collect(
        groupingBy(Dish::getType, mapping(
            dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT; },
            toSet() 
        )));
            
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
    menu.stream().collect(
        groupingBy(Dish::getType, mapping(
            dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT; },
            toCollection(HashSet::new) 
        )));
```

**分区**
```
Map<Boolean, List<Dish>> partitionedMenu =
    menu.stream().collect(partitioningBy(Dish::isVegetarian));
    
```

Collectors类的静态工厂方法：
- toList
- toSet
- toCollection
- counting
- summingInt
- averagingInt
- sumarizingInt
- joining
- maxBy
- minBy
- reducing
- collectingAndThen
- groupingBy
- partitioningBy


#### 13.
- parallel()   并行流
- sequential()  顺序流

#### 14. 从匿名类到 Lambda 表达式的转换
某些情况下，将匿名类转换为Lambda表达式可能是一个比较复杂的过程。首先，匿名类和Lambda表达式中的 this 和 super 的含义是不同的。在匿名类中， this 代表的是类自身，但是在Lambda中，它代表的是包含类。其次，匿名类可以屏蔽包含类的变量，而Lambda表达式不能（它们会导致编译错误）。

#### 15. 从命令式的数据处理切换到 Stream
我们建议你将所有使用迭代器这种数据处理模式处理集合的代码都转换成Stream API的方式。为什么呢？Stream API能更清晰地表达数据处理管道的意图。

#### 17. 调试
- peek

#### 18.
Java 8中的接口现在支持在声明方法的同时提供实现

- 允许在接口内声明静态方法
- 默认方法，通过默认方法, 可以指定接口方法的默认实现

**Java 8中的抽象类和抽象接口**
- 一个类只能继承一个抽象类，但是一个类可以实现多个接口
- 一个抽象类可以通过实例变量（字段）保存一个通用状态，而接口是不能有实例变量

#### 19.
如果一个类使用相同的函数签名从多个地方（比如另一个类或接口）继承了方法，通过三条规则可以进行判断:

- 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级
- 如果无法依据第一条进行判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果 B 继承了 A ，那么 B 就比 A 更加具体
- 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现


#### 20.
Optional<T> 类（ java.util.Optional ）是一个容器类，代表一个值存在或不存在。
- .empty()
- .filter()
- .map()
- .flatmap()
- .of()
- .ofNullable()
- .isPresent()
- .ifPresent(Consumer<T> block)
- .get()  不安全，会抛NoSuchElementException异常
- .orElse(T other)
- .orElseGet(Supplier<? extends T> other)  orElse 方法的延迟调用版
- .orElseThrow(Supplier<? extends X> exceptionSupplier) 可以定制希望抛出的异常类型

```
Optional<Object> value = Optional.ofNullable(map.get("key"));
```

#### 21.

- LocalDate
- LocalTime
- Instant
- Duration
- Period

## 函数式编程
Q: 什么是函数式编程？
A: 它是一种使用函数进行编程的方式.

在函数式编程的上下文中，一个“函数”对应于一个数学函数：它接受零个或多个参数，生成一个或多个结果，并且不会有任何副作用。

**准则**: 被称为“函数式”的函数或方法都只能修改本地变量, 除此之外，它引用的对象都应该是不可修改的对象。要被称为函数式，函数或者方法不应该抛出任何异常。

函数无论在何处、何时调用，如果使用同样的输入总能持续地得到相同的结果，就具备了函数式的特征。

#### 1. 声明式编程
函数式编程具体实践了前面介绍的声明式编程和无副作用计算。

#### 2.
函数式编程的世界里，如果函数，比如 Comparator.comparing ，能满足下面任一要求就
可以被称为高阶函数（higher-order function）：

```
Comparator<Apple> c = comparing(Apple::getWeight);
```

- 接受至少一个函数作为参数
- 返回的结果是一个函数


#### 22.
我们有必要提醒你，即使是传统的面向对象设计也已经不推荐使用 switch 了，现在大家更推荐的方式是采用一些设计模式，比如访问者模式，
使用访问者模式时，程序利用 dispatch 方法，依据数据类型来选择相应的控制流，不再使用传统的 switch 方式。
这并非另一种编程语言中的事——函数式编程语言中使用基于数据类型的模式匹配通常也是设计程序最便捷的方式。

#### 23.
Java 8只支持三种类型的值，分别为：
- 简单类型值
- 指向对象的引用
- 指向函数的引用

#### 24.
Java 8在两个方面对注解机制进行了改进，分别为：
- 你现在可以定义重复注解
- 使用新版Java，你可以为任何类型添加注解

```
@Repeatable(Authors.class)
@interface Author { String name(); }
@interface Authors {
    Author[] value();
}
```

```
public static void main(String[] args) {
    Author[] authors = Book.class.getAnnotationsByType(Author.class);
    Arrays.asList(authors).forEach(a -> { System.out.println(a.name()); });
}
```

```
@NonNull String name = person.getName();
List<@NonNull Car> cars = new ArrayList<>();
```

