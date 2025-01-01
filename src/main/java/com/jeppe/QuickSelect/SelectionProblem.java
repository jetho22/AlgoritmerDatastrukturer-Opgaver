package com.jeppe.QuickSelect;

import java.util.ArrayList;
import java.util.Collections;

public class SelectionProblem {

    // Priority Queue implemented as a Max-Heap
    static class PriorityQueue {
        private final ArrayList<Integer> heap = new ArrayList<>();

        // Insert an element into the max-heap
        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1);
        }

        // Remove the maximum element from the heap
        public int removeMax() {
            if (heap.size() == 0) throw new IllegalStateException("Heap is empty");
            int max = heap.get(0);
            Collections.swap(heap, 0, heap.size() - 1);
            heap.remove(heap.size() - 1);
            siftDown(0);
            return max;
        }

        // Get the maximum element (root of the heap)
        public int getMax() {
            if (heap.size() == 0) throw new IllegalStateException("Heap is empty");
            return heap.get(0);
        }

        // Maintain heap property by sifting up
        private void siftUp(int index) {
            while (index > 0) {
                int parent = (index - 1) / 2;
                if (heap.get(index) <= heap.get(parent)) break;
                Collections.swap(heap, index, parent);
                index = parent;
            }
        }

        // Maintain heap property by sifting down
        private void siftDown(int index) {
            int size = heap.size();
            while (index < size) {
                int left = 2 * index + 1;
                int right = 2 * index + 2;
                int largest = index;

                if (left < size && heap.get(left) > heap.get(largest)) largest = left;
                if (right < size && heap.get(right) > heap.get(largest)) largest = right;

                if (largest == index) break;

                Collections.swap(heap, index, largest);
                index = largest;
            }
        }

        // Get the size of the heap
        public int size() {
            return heap.size();
        }
    }

    // Selection problem using Priority Queue
    public static int quickSelectWithPQ(int[] array, int k) {
        PriorityQueue pq = new PriorityQueue();
        int operationCount = 0;

        for (int num : array) {
            pq.insert(num);
            operationCount++;

            // If the size of the heap exceeds k, remove the max element
            if (pq.size() > k) {
                pq.removeMax();
                operationCount++;
            }
        }

        System.out.println("Number of operations performed: " + operationCount);
        return pq.getMax(); // The k-th smallest element is at the root
    }

    public static void main(String[] args) {
        int[] array = new int[1000]; // Generate a random array of size 1000
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10000);
        }

        int k = 10; // Find the 10th smallest element
        int kthSmallest = quickSelectWithPQ(array, k);
        System.out.println("The " + k + "-th smallest element is: " + kthSmallest);
    }
}

