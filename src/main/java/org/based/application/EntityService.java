package org.based.application;

import java.util.List;

public interface EntityService<T> {
    void save(T entity);
    List<T> findAll();
    void deleteByName(String name);
    T findByName(String name);
}
