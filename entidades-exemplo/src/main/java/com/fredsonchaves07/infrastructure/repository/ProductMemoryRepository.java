package com.fredsonchaves07.infrastructure.repository;

import com.fredsonchaves07.domain.entity.Product;
import com.fredsonchaves07.domain.repository.ProductRepository;
import com.fredsonchaves07.infrastructure.db.MemoryDB;

import java.util.List;

public class ProductMemoryRepository implements ProductRepository {

    private MemoryDB<Product> memoryDB;

    public ProductMemoryRepository() {
        memoryDB = new MemoryDB<>();
    }

    @Override
    public void create(Product entity) {
        memoryDB.add(entity.getId(), entity);
    }

    @Override
    public void update(Product entity) {
        memoryDB.update(entity.getId(), entity);
    }

    @Override
    public Product findById(String id) {
        return memoryDB.find(id);
    }

    @Override
    public List<Product> findAll() {
        return memoryDB.findAll();
    }
}
