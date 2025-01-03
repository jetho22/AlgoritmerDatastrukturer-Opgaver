package com.jeppe.recursion;

public class SumOfAllIntegersInNumber {

    public static int recursiveSum(int N) {

        if (N == 1) {
            return 1;
        }
        return N + (recursiveSum(N-1));
    }

    public static void main(String[] args) {
        System.out.println(recursiveSum(1));
    }
}
