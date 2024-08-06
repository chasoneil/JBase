package com.chason.design_pattern.observer.standard;

public class Custom implements ProductObserver {

    public String name;

    public Custom(String name) {
        this.name = name;
    }

    @Override
    public void onPublished(Product product) {
        System.out.println(name + " known new product " + product.name);
    }

    @Override
    public void onPriceChanged(Product product) {
        System.out.println(name + " known product " + product.name + " price changed, new price:" + product.price);
    }
}
