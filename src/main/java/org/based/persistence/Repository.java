package org.based.persistence;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Repository<T> {
    void save(@NotNull T entity);
    @Nullable List<T> findAll();
    void deleteByName(@NotNull String name);
    Optional<T> findByName(@NotNull String name);
    void update(@NotNull T entity);
}
