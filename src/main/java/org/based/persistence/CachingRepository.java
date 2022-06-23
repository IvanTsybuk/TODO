package org.based.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public class CachingRepository<T extends Entity> implements FileRepository<T> {
    private final Map<String, T> repositoryMap;
    private final Writer<T> writer;
    public CachingRepository(Writer<T> writer) {
        this.writer = writer;
        this.repositoryMap = writer.readFile();
    }
    public void save(@NotNull T entity) {
        repositoryMap.put(entity.getName(), entity);
    }
    public List<T> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
    public void deleteByName(@NotNull String name) {
        repositoryMap.remove(name);
    }
    public Optional<T> findByName(@NotNull String name) {
        return Optional.ofNullable(repositoryMap.get(name));
    }
    public void update(@NotNull T entity) {
        repositoryMap.put(entity.getName(), entity);
    }
    public void saveToFile() {
        writer.writeToFile(repositoryMap);
    }
}
