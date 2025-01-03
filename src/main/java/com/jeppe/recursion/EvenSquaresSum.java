package com.jeppe.recursion;

public class EvenSquaresSum {

    // Recursive method to calculate the sum of squares of even numbers
    public static int sumEvenSquares(int n) {
        if (n <= 0) {
            return 0; // Base case: no numbers to sum
        }
        if (n % 2 == 0) {
            return (n * n) + sumEvenSquares(n - 2); // Add n^2 for even n
        } else {
            return sumEvenSquares(n - 1); // Skip odd n
        }
    }

    public static void main(String[] args) {
        int n = 9; // Example input
        System.out.println("Sum of squares of even numbers from 1 to " + n + " is: " + sumEvenSquares(n));
    }
}
