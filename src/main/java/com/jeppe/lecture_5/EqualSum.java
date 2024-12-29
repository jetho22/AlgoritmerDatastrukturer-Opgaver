package com.jeppe.lecture_5;

import java.util.Arrays;

public class EqualSum {

    // List is always sorted

    public static boolean quadraticTime(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) return true;
            }
        }
        return false;
    }

    public static boolean linearTime(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int sum = array[left] + array[right];

            if (sum == target) return true;

            if (sum < target) {
                // move left pointer to the right for bigger numbers
                left++;
            } else {
                // move right pointer to the left for smaller numbers
                right++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int target = 9;
        System.out.println(Arrays.toString(array));
        System.out.println("Target: " + target);
        System.out.println("Quadratic time: " + quadraticTime(array, target));
        System.out.println("Linear time: " + linearTime(array, target));
    }
}
