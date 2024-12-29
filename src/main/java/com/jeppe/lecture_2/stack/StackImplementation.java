package com.jeppe.lecture_2.stack;

@SuppressWarnings("unchecked")
public class StackImplementation<T> implements Stack<T> {
    private T[] elements;
    private int topIndex;

    public StackImplementation(int size) {
        this.elements = (T[]) new Object[size];
        this.topIndex = -1;
    }

    @Override
    public T push(T element) {
        if (topIndex == (elements.length - 1)) {
            resize();
        }

        elements[++topIndex] = element;
        return element;
    }

    private void resize() {
        T[] newArray = (T[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    @Override
    public T pop() {
        if (topIndex == -1) {
            throw new IllegalStateException("Stack is empty");
        }

        T topElement = elements[topIndex];
        elements[topIndex] = null;
        topIndex--;

        return topElement;
    }

    @Override
    public T peek() {
        if (topIndex == -1) {
            throw new IllegalStateException("Stack is empty");
        }

        return elements[topIndex];
    }

    @Override
    public boolean isEmpty() {
        return (topIndex == -1);
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

}
