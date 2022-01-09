package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.Repository;

public class TaskService {
    private final Repository<Task> repository;
    public TaskService(final Repository<Task> repository) {
        this.repository = repository;
    }
    public void createTask(final Task task) {
        repository.save(task);
    }
    public List<Task> findAll() {
        return repository.findAll();
    }
    public void removeByName(String taskName) {
        repository.delete(taskName);
    }
}
