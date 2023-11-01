package com.fredsonchaves07.infrastructure.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryDB<T> {

    Map<String, T> data = new HashMap<>();

    public void clear() {
        data = new HashMap<>();
    }

    public void add(String id, T element) {
        data.put(id, element);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void update(String id, T element) {
        data.replace(id, element);
    }

    public List<T> findAll() {
        return data.values().stream().toList();
    }
}
