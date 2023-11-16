package com.fredsonchaves07.domain.product.factory;

import com.fredsonchaves07.domain.product.entity.Product;

import java.util.UUID;

public final class ProductFactory {

    private ProductFactory() {
    }

    ;

    public static Product create(String name, double price) {
        String id = UUID.randomUUID().toString();
        return new Product(id, name, price);
    }
}
