package org.based.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CachingRepository<T extends Entity> implements FileRepository<T> {
    private final Map<String, T> repositoryMap;
    private final Writer<T> writer;
    public CachingRepository(Writer<T> writer) {
        this.writer = writer;
        this.repositoryMap = writer.readFile();
    }
    public void save(T entity) {
        repositoryMap.put(entity.getName(), entity);
    }
    public List<T> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
    public void deleteByName(String name) {
        repositoryMap.remove(name);
    }
    public T findByName(String name) {
        return repositoryMap.get(name);
    }
    public void update(T entity) {
        repositoryMap.put(entity.getName(), entity);
    }
    public void saveToFile() {
        writer.writeToFile(repositoryMap);
    }
}
