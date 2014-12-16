package com.company;

/**
 * Created by vladimir_danilov on 12/11/2014.
 */ ////////////////////////
// Defines a Link class
//
// used by LinkedList
////////////////////////
class Link {
    private Object valueField;
    private Link nextLink;

    public Link(Object newValue, Link next) {
        valueField = newValue;
        nextLink = next;
    }

    // get Object part of link
    public Object value() {
        return valueField;
    }

    // get Link part of link
    public Link next() {
        return nextLink;
    }
}
