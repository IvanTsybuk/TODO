package org.based.persistence;

import java.util.List;

public interface RepositoryInterface<T> {
    void save(T entity);
    List<T> findAll();
    void delete(String name);
    T findByName(String name);
    void saveToFile();
}
