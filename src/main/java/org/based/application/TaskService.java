package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
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
    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }
    public void update(Task task) {
        taskRepository.update(task);
    }
}
