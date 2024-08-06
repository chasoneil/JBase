package com.chason.design_pattern.observer.standard;

import java.util.*;

public class Store {

    public String name;

    public Store (String name) {
        this.name = name;
        System.out.println("Store " + name + " opened.");
    }

    Map<String, Product> products = new HashMap<>();

    Set<ProductObserver> observers = new HashSet<>();

    public void addProduct(Product product) {
        products.put(product.name, product);

        // notice all observers
        for (ProductObserver observer : observers) {
            observer.onPublished(product);
        }
    }

    public void changePrice(Product product, double newPrice) {

        Product p = products.get(product.name);
        if (p == null) {
            throw new RuntimeException("Product " + product.name + " not exist");
        }

        p.price = newPrice;

        // notice all observers
        for (ProductObserver observer : observers) {
            observer.onPriceChanged(product);
        }
    }

    public void addObserver (ProductObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProductObserver observer) {
        observers.remove(observer);
    }


}
