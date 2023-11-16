package com.fredsonchaves07.domain.factory;

import com.fredsonchaves07.domain.product.entity.Product;
import com.fredsonchaves07.domain.product.factory.ProductFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductFactoryTest {

    @Test
    public void shouldCreateAProduct() {
        Product product = ProductFactory.create("Product A", 100);
        assertNotNull(product.getId());
        assertEquals("Product A", product.getName());
        assertEquals(100, product.getPrice());
    }
}
