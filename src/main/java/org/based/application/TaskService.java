package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.RepositoryInterface;

public class TaskService {
    private final RepositoryInterface<Task> jdbcTaskRepository;
    public TaskService(RepositoryInterface<Task> jdbcTaskRepository) {
        this.jdbcTaskRepository = jdbcTaskRepository;
    }
    public void createTask(final Task task) {
        jdbcTaskRepository.save(task);
    }
    public List<Task> findAll() {
        return jdbcTaskRepository.findAll();
    }
    public void deleteTask(String taskName) {
        jdbcTaskRepository.delete(taskName);
    }
}
