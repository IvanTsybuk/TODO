package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;

import java.util.*;

@Data
public class Repository<T> implements Entity {

    private final Map<String, T> repositoryMap;
    private final AbstractOperator abstractOperator;
    private final TypeReference<HashMap<String, T>> typeReference;

    public Repository(AbstractOperator abstractOperator, TypeReference<HashMap<String, T>> typeReference) {
        this.abstractOperator = abstractOperator;
        this.typeReference = typeReference;
        repositoryMap = getMap();
    }

    @Override
    public void save(Task task) {
        repositoryMap.put(task.getName(), (T) task);
    }
    @Override
    public void save(User user) {
        repositoryMap.put(user.getUserSurName(), (T) user);
    }

    @Override
    public void save(Project project) {
        repositoryMap.put(project.getName(), (T) project);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }

    @Override
    public void delete(String name) {
        repositoryMap.remove(name);
    }

    @Override
    public User findByName(String surName) {
        return (User) repositoryMap.get(surName);
    }

    public Map getMap() {
       return abstractOperator.readFile(typeReference);
    }
    public void sendRepository(){
        abstractOperator.writeToFile(repositoryMap);
    }
}
