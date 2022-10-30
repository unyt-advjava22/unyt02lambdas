package com.eltonb.textbook.ex00;

import java.time.Duration;
import java.time.Instant;

public class Boxing {
    public static void main(String[] args) {
        Instant start = Instant.now();
        test();
        Instant finish = Instant.now();
        System.out.println("duration is " + Duration.between(start, finish).toMillis());
    }

    private static void test() {
        Long sum = 0L;
        for (int i = 0; i< Integer.MAX_VALUE; i++) {
            sum = sum + i;
        }
        System.out.println("sum is " + sum);
    }
}
