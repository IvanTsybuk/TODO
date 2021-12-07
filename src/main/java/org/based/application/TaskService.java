package org.based.application;

import org.based.domain.Task;
import org.based.persistence.Repository;
import java.util.List;

public class TaskService {

    private final Repository<Task>repository;

    public TaskService(Repository<Task>repository) {
        this.repository = repository;
    }

    public void createTask(Task task) {
        repository.save(task);
    }
    public List<Task> getTasks() {
        return repository.findAll();
    }
    public void removeByName(String taskName) {
        repository.delete(taskName);
    }
}
