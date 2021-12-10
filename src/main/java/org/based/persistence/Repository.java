package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.*;

public class Repository<T extends Entity> {
    private final Map<String, T> repositoryMap;
    private final AbstractWriter abstractWriter;
    private final TypeReference<HashMap<String, T>> typeReference;
    public Repository(AbstractWriter abstractWriter, TypeReference<HashMap<String, T>> typeReference) {
        this.abstractWriter = abstractWriter;
        this.typeReference = typeReference;
        repositoryMap = (Map<String, T>) abstractWriter.readFile(typeReference);
    }
    private Map getMap() {
        return abstractWriter.readFile(typeReference);
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
        return (T) repositoryMap.get(name);
    }
    public void sendRepository(){
        abstractWriter.writeToFile(repositoryMap);
    }
}
