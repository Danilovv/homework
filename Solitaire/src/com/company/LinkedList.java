package com.company;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ //////////////////////////////
// Defines a LinkedList class
//
// used by CardPile
//////////////////////////////
class LinkedList {
    private Link firstLink;

    public LinkedList() {
        firstLink = null;
    }

    // true if empty, false otherwise
    public boolean empty() {
        return firstLink == null;
    }

    // add an Object to our list
    public void add(Object newValue) {
        firstLink = new Link(newValue, firstLink);
    }

    // inspect front of list
    public Object front() {
        if (firstLink == null)
            return null;

        return firstLink.value();
    }

    // pop front of list
    public Object pop() {
        if (firstLink == null)
            return null;

        Object result = firstLink.value();
        firstLink = firstLink.next();
        return result;
    }

    // return an iterator for the list
    public ListIterator iterator() {
        return new ListIterator(firstLink);
    }
}
