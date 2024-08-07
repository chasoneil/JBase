package com.chason.algrithm.class02.base;

/**
 * double linked list
 * @param <T>
 */
public class DNode<T> {

    public T value;

    public DNode next;

    public DNode prev;

    public DNode(T data) {
        this.value = data;
    }


}
