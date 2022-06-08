package org.based.persistence;

import java.util.List;

public interface Repository<T> {
    void save(T entity);
    List<T> findAll();
    void deleteByName(String name);
    T findByName(String name);
}
