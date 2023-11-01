package com.fredsonchaves07.domain.repository;

import java.util.List;

public interface Repository<T> {

    void create(T entity);

    void update(T entity);

    T findById(String id);

    List<T> findAll();
}
