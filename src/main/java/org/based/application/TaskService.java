package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.Repository;

public class TaskService {
    private final Repository<Task> taskRepository;
    public TaskService(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void save(final Task task) {
        taskRepository.save(task);
    }
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public void deleteByName(String taskName) {
        taskRepository.deleteByName(taskName);
    }
}
