package com.jeppe.lecture_1;

public class Fibonacci {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        return fib(n - 2) + fib(n - 1);
    }



}
