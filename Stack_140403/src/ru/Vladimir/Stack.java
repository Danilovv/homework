package ru.Vladimir;

/**
 * Created by Vladimir_Danilov on 18-Dec-14.
 */
public class Stack {

    Object[] items;
    private int index = 0;

    public Stack(int maxSize) {
        items = new Object[maxSize];
    }

    public void push(Object item) {
        items[index++] = item;
    }

    public Object pop() {
        return items[--index];
    }

    public Object top() {
        return items[index-1];
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return index;
    }
}
