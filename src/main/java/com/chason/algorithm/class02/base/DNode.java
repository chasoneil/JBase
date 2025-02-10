package com.chason.algorithm.class02.base;

/**
 * double linked list
 * @param <T>
 */
public class DNode<T> {

    public T value;

    public DNode<T> next;

    public DNode<T> prev;

    public DNode(T data) {
        this.value = data;
    }


}
