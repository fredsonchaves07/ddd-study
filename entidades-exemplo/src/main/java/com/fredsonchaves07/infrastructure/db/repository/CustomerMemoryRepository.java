package com.fredsonchaves07.infrastructure.db.repository;

import com.fredsonchaves07.domain.customer.entity.Customer;
import com.fredsonchaves07.domain.customer.repository.CustomerRepository;
import com.fredsonchaves07.infrastructure.db.MemoryDB;

import java.util.List;

public class CustomerMemoryRepository implements CustomerRepository {

    private MemoryDB<Customer> memoryDB;

    public CustomerMemoryRepository() {
        memoryDB = new MemoryDB<>();
    }

    @Override
    public void create(Customer entity) {
        memoryDB.add(entity.getId(), entity);
    }

    @Override
    public void update(Customer entity) {
        memoryDB.update(entity.getId(), entity);
    }

    @Override
    public Customer findById(String id) {
        return memoryDB.find(id);
    }

    @Override
    public List<Customer> findAll() {
        return memoryDB.findAll();
    }
}
