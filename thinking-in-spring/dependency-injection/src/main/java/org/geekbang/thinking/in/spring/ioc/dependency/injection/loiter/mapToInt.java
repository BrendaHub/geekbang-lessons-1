package org.geekbang.thinking.in.spring.ioc.dependency.injection.loiter;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Fun Description //TODO
 * @Date 2020/6/26 23:08 26
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class mapToInt {
    public static void main(String[] args) {

        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("1", "2"),
                Arrays.asList("5", "6"),
                Arrays.asList("3", "4")
        );

        List<String> secondList = Arrays.asList(
                "12", "13", "14"
        );

        IntStream intStream = listOfLists.parallelStream()
                .flatMapToInt(item -> item.parallelStream()
                        .mapToInt(i -> new Integer(i)));

//        intStream.forEach(System.out::println);
//        int sum = intStream.sum();
//        System.out.println("sum = " + sum);

        int _sum = intStream.peek(System.out::println).sum();
        System.out.println("_sum = " + _sum);


//        int sum3 = listOfLists.parallelStream().flatMapToInt(item -> item.parallelStream().mapToInt(Integer::new))
//                .flatMap(item -> secondList.parallelStream().mapToInt(Integer::new)).distinct().peek(System.out::println).sum();

        OptionalInt reduce = listOfLists.parallelStream().flatMapToInt(item -> item.parallelStream().mapToInt(Integer::new))
                .flatMap(item -> secondList.parallelStream().mapToInt(Integer::new)).distinct().peek(System.out::println).reduce((x, y) -> x + y);
        System.out.println(reduce.isPresent());
        System.out.println(reduce.getAsInt());

    }
}
