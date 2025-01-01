package com.jeppe.QuickSelect;

import java.util.Random;

public class QuickSelect {

    // QuickSelect algorithm to find the k-th smallest element
    public static int quickSelect(int[] array, int k) {
        return quickSelectHelper(array, 0, array.length - 1, k - 1);
    }

    private static int quickSelectHelper(int[] array, int low, int high, int k) {
        if (low == high) {
            return array[low];
        }

        int pivotIndex = partition(array, low, high);
        if (pivotIndex == k) {
            return array[pivotIndex];
        } else if (pivotIndex > k) {
            return quickSelectHelper(array, low, pivotIndex - 1, k);
        } else {
            return quickSelectHelper(array, pivotIndex + 1, high, k);
        }
    }

    // Partition the array and return the pivot index
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                swap(array, i, j);
                i++;
            }
        }
        swap(array, i, high);
        return i;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[1000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10000);
        }

        int k = 10; // Find the 10th smallest element
        int result = quickSelect(array, k);
        System.out.println("The " + k + "-th smallest element is: " + result);
    }
}

