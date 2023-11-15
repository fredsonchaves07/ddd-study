package com.fredsonchaves07.domain.product.service;

import com.fredsonchaves07.domain.product.entity.Product;

import java.util.List;

public class ProductService {

    public static void increasePrices(List<Product> products, int percent) {
        products.forEach(product -> product.changePrice((product.getPrice() * percent) / 100 + product.getPrice()));
    }
}
