package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.exceptions.EntityConstraintException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private static final String CONSTRAINT_VIOLATION = "Task with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Task with name - %s, is not found";
    private final Repository<Task> taskRepository;
    public TaskService(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void save(final Task task) {
        if (taskRepository.findByName(task.getName()).isPresent()) {
            throw new EntityConstraintException(
                    String.format(CONSTRAINT_VIOLATION, task.getName()));
        }
        taskRepository.save(task);
    }
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public void deleteByName(String taskName) {
        taskRepository.deleteByName(taskName);
    }
    public Task findByName(String name) {
        return taskRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void update(Task task) {
        taskRepository.update(task);
    }
}
