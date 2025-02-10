package com.chason.algorithm.class02.base;

/**
 * single linked list
 * @param <T>
 */
public class Node<T> {

    public T value;

    public Node<T> next;

    public Node(T data) {
        this.value = data;
    }

}
