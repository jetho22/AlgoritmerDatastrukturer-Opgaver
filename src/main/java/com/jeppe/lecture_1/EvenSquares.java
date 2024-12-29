package com.jeppe.lecture_1;

public class EvenSquares {
    public static int evenSquares(int n) {
        if (n < 1) {
            return 0;
        }

        int evenNumber = 2 * n;
        int square = evenNumber * evenNumber;

        return square + evenSquares(n - 1);
    }
}
