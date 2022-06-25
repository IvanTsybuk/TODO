package org.based.persistence;

import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface Repository<T> {
    void save(@NotNull T entity);
    @NotNull List<T> findAll();
    void deleteByName(@NotNull String name);
    @NotNull Optional<T> findByName(@NotNull String name);
    void update(@NotNull T entity);
}
