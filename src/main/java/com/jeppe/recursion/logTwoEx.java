package com.jeppe.recursion;

public class logTwoEx {

    public static int logTwo(int N) {

        if (N == 1) {
            return 0;
        }
        return 1 + logTwo(N / 2);
    }

    public static void main(String[] args) {
        int b = 32;
        int c = 4096;
        System.out.println(logTwo(b));
        System.out.println(logTwo(c));

    }
}


