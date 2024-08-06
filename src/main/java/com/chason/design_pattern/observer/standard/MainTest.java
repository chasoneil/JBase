package com.chason.design_pattern.observer.standard;

public class MainTest {

    public static void main(String[] args) {

        Store store = new Store("Sam's Club");

        Custom custom1 = new Custom("zhangsan");
        Custom custom2 = new Custom("lisi");

        store.addObserver(custom1);
        store.addObserver(custom2);

        Product product1 = new Product("chicken",20.0);
        store.addProduct(product1);

        Product product2 = new Product("iphone14", 5999);
        store.addProduct(product2);


        store.changePrice(product2, 4999);

    }

}
