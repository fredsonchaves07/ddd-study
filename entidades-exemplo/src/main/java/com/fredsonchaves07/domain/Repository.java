package com.fredsonchaves07.domain;

import java.util.List;

public interface Repository<T> {

    void create(T entity);

    void update(T entity);

    T findById(String id);

    List<T> findAll();
}
