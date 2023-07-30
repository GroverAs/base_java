package com.urise.webapp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamTask2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(4);
        list.add(6);
        list.add(5);
        list.add(3);

        System.out.println(oddOrEven(list));
    }


    private static List<Integer> oddOrEven(List<Integer> integers) {
        Integer sum = integers.stream()
                .reduce(0, Integer::sum);

        return integers.stream()
                .filter(a -> (sum % 2 != 0) == (a % 2 == 0))
                .collect(toList());
    }
}

