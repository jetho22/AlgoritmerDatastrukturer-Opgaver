package com.jeppe.lecture_2.queue;

public class QueueImplementation<T> implements Queue<T> {
    private T[] elements;
    private int size;
    private int front;
    private int rear;

    public QueueImplementation(int size) {
        this.elements = (T[]) new Object[size];
        this.size = size;
        this.front = -1;
        this.rear = -1;
    }

    @Override
    public void enqueue(T element) {
        if ((rear + 1) % size == front) {
            throw new IllegalStateException("Queue is full");
        }

        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % size;

        elements[rear] = element;
    }

    @Override
    public T dequeue() {
        if (front == -1) {
            throw new IllegalStateException("Queue is empty");
        }

        T element = elements[front];
        elements[front] = null;
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % size;
        }

        return element;
    }
}
