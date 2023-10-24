package com.fredsonchaves07.services;

import com.fredsonchaves07.entity.Product;

import java.util.List;

public class ProductService {

    public static void increasePrices(List<Product> products, int percent) {
        products.forEach(product -> product.changePrice((product.getPrice() * percent) / 100 + product.getPrice()));
    }
}