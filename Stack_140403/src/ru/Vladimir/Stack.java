package ru.Vladimir;

/**
 * Created by Vladimir_Danilov on 18-Dec-14.
 */
public class Stack<T> {

    T[] items;
    private int index = 0;

    public Stack(int maxSize) {
        //items =  new Object[maxSize];
        items = (T[])  new Object[maxSize];
    }

    public void push(T item) throws StackOverflowError {
        if(getSize() == items.length) {
            throw new StackOverflowError();
        }
        items[index++] = item;
    }

    public T pop() {
        if(isEmpty()) return null;
        return items[--index];
    }

    public T top() {
        if(isEmpty()) return null;
        return items[index-1];
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return index;
    }
}
