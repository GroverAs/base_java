package com.urise.webapp.util;

import java.util.Arrays;


public class StreamTask1 {
    public static void main(String[] args) {
        int[] a = new int[]{8, 2, 4, 4, 8, 8, 8, 1, 9};
        System.out.println(minValue(a));
    }

    public static int minValue(int[] values) {

        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce((left, right) -> left * 10 + right)
                .orElseThrow();
    }

}



