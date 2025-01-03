package com.jeppe.finalexercises;

public class PythagoreanTriplet {
    public static void main(String[] args) {
        // Loop through all possible values for a and b
        for (int a = 1; a < 1000; a++) {
            for (int b = a + 1; b < 1000 - a; b++) {
                int c = 1000 - a - b; // Calculate c

                // Check if it's a Pythagorean triplet
                if (a * a + b * b == c * c) {
                    System.out.println("Triplet found: a=" + a + ", b=" + b + ", c=" + c);
                    System.out.println("Product: " + (a * b * c));
                    return; // Exit after finding the first valid triplet
                }
            }
        }
    }
}