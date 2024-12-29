package com.jeppe.lecture_2.stack;

public interface Stack<T> {
    T push(T element);

    T pop();

    T peek();

    boolean isEmpty();

    int size();
}
