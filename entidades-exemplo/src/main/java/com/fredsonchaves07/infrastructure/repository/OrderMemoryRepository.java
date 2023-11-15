package com.fredsonchaves07.infrastructure.repository;

import com.fredsonchaves07.domain.checkout.entity.Order;
import com.fredsonchaves07.domain.checkout.repository.OrderRepository;
import com.fredsonchaves07.infrastructure.db.MemoryDB;

import java.util.List;

public class OrderMemoryRepository implements OrderRepository {

    private MemoryDB<Order> memoryDB;

    public OrderMemoryRepository() {
        memoryDB = new MemoryDB<>();
    }

    @Override
    public void create(Order entity) {
        memoryDB.add(entity.getId(), entity);
    }

    @Override
    public void update(Order entity) {
        memoryDB.update(entity.getId(), entity);
    }

    @Override
    public Order findById(String id) {
        return memoryDB.find(id);
    }

    @Override
    public List<Order> findAll() {
        return memoryDB.findAll();
    }
}
