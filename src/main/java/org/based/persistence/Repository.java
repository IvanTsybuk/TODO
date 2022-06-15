package org.based.persistence;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void save(T entity);
    List<T> findAll();
    void deleteByName(String name);
    Optional<T> findByName(String name);
    void update(T entity);
}
