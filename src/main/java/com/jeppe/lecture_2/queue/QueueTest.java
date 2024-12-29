package com.jeppe.lecture_2.queue;

public class QueueTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new QueueImplementation<>(5);

        // Test enqueue operation
        queue.enqueue(5);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        // Test queue full condition
        try {
            queue.enqueue(6);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected: Queue is full
        }

        // Test dequeue operation
        System.out.println(queue.dequeue()); // Expected: 1
        System.out.println(queue.dequeue()); // Expected: 2

        // Test enqueue after dequeue
        queue.enqueue(6);
        System.out.println(queue.dequeue()); // Expected: 3
        System.out.println(queue.dequeue()); // Expected: 4
        System.out.println(queue.dequeue()); // Expected: 5
        System.out.println(queue.dequeue()); // Expected: 6

        // Test queue empty condition
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected: Queue is empty
        }
    }
}
