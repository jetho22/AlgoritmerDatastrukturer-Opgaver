package com.jeppe.recursion;

public class RecursiveCount {

    public static int getNumberEqual(int[] x, int n, int val) {
        // Base case: If the array is empty (n == 0), return 0
        if (n == 0) {
            return 0;
        }

        // Recursive case: Check the last element and count it if it matches `val`
        int count = (x[n - 1] == val) ? 1 : 0;

        // Recur for the rest of the array
        return count + getNumberEqual(x, n - 1, val);
    }

    public static void main(String[] args) {
        int[] x = {7, 4, 1, 3, 5, 6, 4, 8};
        int n = x.length;
        int val = 4;

        int result = getNumberEqual(x, n, val);
        System.out.println("Occurrences of " + val + ": " + result);
    }
}
