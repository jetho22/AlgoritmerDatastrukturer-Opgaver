package com.jeppe.finalexercises;

public class PasswordCalculator {

    // Function to calculate factorial
    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    // Function to calculate combinations
    public static int combinations(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public static void main(String[] args) {
        int n = 9; // Total digits (1 to 9)
        int r = 6; // Password length

        // Calculate total combinations
        int totalPasswords = combinations(n, r);

        // Output the result
        System.out.println("Total number of valid passwords: " + totalPasswords);
    }
}
