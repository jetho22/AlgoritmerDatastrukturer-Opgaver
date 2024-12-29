package com.jeppe.lecture_2.queue;

public interface Queue<T> {
    void enqueue(T element);

    T dequeue();
}
