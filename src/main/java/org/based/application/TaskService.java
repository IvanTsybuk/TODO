package org.based.application;

import org.based.domain.Task;
import org.based.persistence.Repository;
import java.util.List;

public class TaskService {

    private final Repository<Task>repository;

    public TaskService(Repository<Task>repository) {
        this.repository = repository;

    }

    public void addTask(Task task) {
        repository.save(task);
    }

    public List<Task> getTaskList() {
        return repository.findAll();
    }

    public void removeSelectedTask(String taskName) {
        repository.delete(taskName);
    }
}
