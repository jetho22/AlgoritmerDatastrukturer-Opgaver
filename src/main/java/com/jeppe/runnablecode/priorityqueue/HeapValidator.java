package com.jeppe.runnablecode.priorityqueue;

public class HeapValidator {

    public static boolean validateMinHeap(Integer[] heapArray) {
        boolean isValid = true;
        if (heapArray == null || heapArray.length <= 1) {
            return true;  // empty or single-element array is valid
        }

        for (int i = 1; i < heapArray.length; i++) {
            if (heapArray[i] == null) {
                continue;  // skip null elements
            }

            // Check left child
            int leftChild = 2 * i;
            if (leftChild < heapArray.length && heapArray[leftChild] != null) {
                if (heapArray[i].compareTo(heapArray[leftChild]) > 0) {
                    System.out.println("Heap property violated at index " + i + ": parent " + heapArray[i] + " is greater than left child " + heapArray[leftChild]);
                    System.out.println("Fix interval for left child: [" + heapArray[i] + ", " + Integer.MAX_VALUE + "]");
                    isValid = false;  // parent is greater than left child
                }
            }

            // Check right child
            int rightChild = 2 * i + 1;
            if (rightChild < heapArray.length && heapArray[rightChild] != null) {
                if (heapArray[i].compareTo(heapArray[rightChild]) > 0) {
                    System.out.println("Heap property violated at index " + i + ": parent " + heapArray[i] + " is greater than right child " + heapArray[rightChild]);
                    System.out.println("Fix interval for right child: [" + heapArray[i] + ", " + Integer.MAX_VALUE + "]");
                    isValid = false;  // parent is greater than right child
                }
            }
        }
        return isValid;
    }

    public static boolean validateMaxHeap(Integer[] heapArray) {
        boolean isValid = true;
        if (heapArray == null || heapArray.length <= 1) {
            return true;  // empty or single-element array is valid
        }

        for (int i = 1; i < heapArray.length; i++) {
            if (heapArray[i] == null) {
                continue;  // skip null elements
            }

            // Check left child
            int leftChild = 2 * i;
            if (leftChild < heapArray.length && heapArray[leftChild] != null) {
                if (heapArray[i].compareTo(heapArray[leftChild]) < 0) {
                    System.out.println("Heap property violated at index " + i + ": parent " + heapArray[i] + " is less than left child " + heapArray[leftChild]);
                    System.out.println("Fix interval for left child: [" + Integer.MIN_VALUE + ", " + heapArray[i] + "]");
                    isValid = false;  // parent is less than left child
                }
            }

            // Check right child
            int rightChild = 2 * i + 1;
            if (rightChild < heapArray.length && heapArray[rightChild] != null) {
                if (heapArray[i].compareTo(heapArray[rightChild]) < 0) {
                    System.out.println("Heap property violated at index " + i + ": parent " + heapArray[i] + " is less than right child " + heapArray[rightChild]);
                    System.out.println("Fix interval for right child: [" + Integer.MIN_VALUE + ", " + heapArray[i] + "]");
                    isValid = false;  // parent is less than right child
                }
            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        Integer[] minHeapArray = {null, 9, 23, 106, 10, 36, 38, 98, 84, 12, 14, 50, 55, 35, 68};

        System.out.println("Is valid min-heap: " + validateMinHeap(minHeapArray));
    }
}
