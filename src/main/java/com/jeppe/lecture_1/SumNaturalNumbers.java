package com.jeppe.lecture_1;

public class SumNaturalNumbers {
    public static int sumNaturalNumbers(int n) {
        if (n < 1) {
            return 0;
        }

        return n + sumNaturalNumbers(n - 1);
    }
}
