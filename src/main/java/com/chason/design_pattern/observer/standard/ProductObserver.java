package com.chason.design_pattern.observer.standard;

public interface ProductObserver {

    void onPublished(Product product);

    void onPriceChanged(Product product);

}
