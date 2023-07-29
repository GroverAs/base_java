package com.urise.webapp.util;

import java.util.Arrays;


public class StreamTask1 {
    public static void main(String[] args) {
        int[] a = new int[]{8, 2, 4, 4, 8, 8, 8, 1, 9};
        System.out.println(minValue(a));
    }

    public static int minValue(int[] values) {


        final int[] result = {0};
        Arrays.stream(values)
                .boxed()
                .distinct()
                .sorted()
                .mapToInt(Integer::intValue)
                .forEach(i -> result[0] = i + result[0] * 10);
        return result[0];

    }

}



