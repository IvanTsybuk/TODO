package org.based.persistence;

import java.util.*;

public class Repository<T extends Entity> {
    private final Map<String, T> repositoryMap;
    private final Writer<T> writer;
    public Repository(Writer<T> writer) {
        this.writer = writer;
        repositoryMap = writer.readFile();
    }
    public void save(T entity){
        repositoryMap.put(entity.getName(), entity);
    }
    public List<T> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
    public void delete(String name) {
        repositoryMap.remove(name);
    }
    public T findByName(String name) {
        return repositoryMap.get(name);
    }
    public void saveToFile(){
        writer.writeToFile(repositoryMap);
    }
}
