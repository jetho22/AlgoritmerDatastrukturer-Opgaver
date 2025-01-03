package com.jeppe.eksamen2;

public class SumAfToTal {

    public static boolean sumAfToTalLigParameterKvadratisk(int[] arr, int l, int X) {
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                if (arr[i] + arr[j] == X) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean sumAfToTalLigParameterLinear(int[] arr, int l, int X) {
        int left = 0; // Venstre pointer
        int right = l - 1; // Højre pointer

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == X) {
                return true;
            } else if (sum < X) {
                left++; // Flyt venstre pointer mod højre
            } else {
                right--; // Flyt højre pointer mod venstre
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int X = 10;

        // Test Version 1
        System.out.println("Version 1: " + sumAfToTalLigParameterKvadratisk(arr, arr.length, X)); // Forventet: true

        // Test Version 2
        System.out.println("Version 2: " + sumAfToTalLigParameterLinear(arr, arr.length, X)); // Forventet: true
    }


}
