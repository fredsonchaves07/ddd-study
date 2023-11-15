package com.fredsonchaves07.infrastructure.repository;

import com.fredsonchaves07.domain.product.entity.Product;
import com.fredsonchaves07.domain.product.repository.ProductRepository;
import com.fredsonchaves07.infrastructure.db.repository.ProductMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProductRepositoryMemoryTest {

    @Test
    public void shouldCreateAProduct() {
        ProductRepository productRepository = new ProductMemoryRepository();
        Product product = new Product("1", "Product 1", 100);
        productRepository.create(product);
        Product product1 = productRepository.findById("1");
        assertEquals("1", product1.getId());
        assertEquals("Product 1", product1.getName());
        assertEquals(100, product1.getPrice());
    }

    @Test
    public void shouldUpdateAProduct() {
        ProductRepository productRepository = new ProductMemoryRepository();
        Product product = new Product("1", "Product 1", 100);
        productRepository.create(product);
        product.changePrice(200);
        productRepository.update(product);
        Product product1 = productRepository.findById("1");
        assertEquals("1", product1.getId());
        assertEquals("Product 1", product1.getName());
        assertEquals(200, product1.getPrice());
    }

    @Test
    public void shouldFindAllProducts() {
        ProductRepository productRepository = new ProductMemoryRepository();
        productRepository.create(new Product("1", "Product 1", 100));
        productRepository.create(new Product("2", "Product 2", 300));
        productRepository.create(new Product("3", "Product 3", 500));
        List<Product> products = productRepository.findAll();
        assertFalse(products.isEmpty());
        assertEquals(3, products.size());
        assertEquals("1", products.get(0).getId());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals(100, products.get(0).getPrice());
        assertEquals("2", products.get(1).getId());
        assertEquals("Product 2", products.get(1).getName());
        assertEquals(300, products.get(1).getPrice());
        assertEquals("3", products.get(2).getId());
        assertEquals("Product 3", products.get(2).getName());
        assertEquals(500, products.get(2).getPrice());
    }
}
