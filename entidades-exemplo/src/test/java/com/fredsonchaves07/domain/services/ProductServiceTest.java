package com.fredsonchaves07.domain.services;

import com.fredsonchaves07.domain.product.entity.Product;
import com.fredsonchaves07.domain.product.service.ProductService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {

    @Test
    public void shouldChangeThePricesOfAllProducts() {
        Product product1 = new Product("product123", "product1", 10);
        Product product2 = new Product("product654", "product2", 20);
        List<Product> products = List.of(product1, product2);
        ProductService.increasePrices(products, 100);
        assertEquals(20, product1.getPrice());
        assertEquals(40, product2.getPrice());
    }
}
