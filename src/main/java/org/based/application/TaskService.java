package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.Task;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TaskService {
    private static final String ALREADY_EXIST = "Task with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Task with name - %s, is not found";
    private final Repository<Task> taskRepository;
    public TaskService(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void save(@NotNull final Task task) {
        log.info("Saving new task");
        taskRepository.findByName(task.getName())
                .ifPresent(a -> {
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        taskRepository.save(task);
    }
    @Nullable
    public List<Task> findAll() {
        log.info("Finding all tasks");
        return taskRepository.findAll();
    }
    public void deleteByName(@NotNull String taskName) {
        log.info("Deleting a task");
        taskRepository.deleteByName(taskName);
    }
    public Task findByName(@NotNull String name) {
        log.info("Finding a task by name");
        return taskRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name)));
    }
    public void update(@NotNull Task task) {
        log.info("Updating a task");
        taskRepository.findByName(task.getName()).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, task.getName())));
        taskRepository.update(task);
    }
}
