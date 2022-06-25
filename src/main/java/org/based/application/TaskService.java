package org.based.application;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.domain.Task;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class TaskService {
    private static final String ALREADY_EXIST = "Task with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Task with name - %s, is not found";
    @NotNull
    private final Repository<Task> taskRepository;
    public TaskService(@NotNull Repository<Task> taskRepository) {
        log.info("TaskService initialization");
        this.taskRepository = taskRepository;
    }
    public void save(@NotNull final Task task) {
        log.debug("TaskService: Save new task");
        taskRepository.findByName(task.getName())
                .ifPresent(a -> {
                    log.error("TaskService Exception: save new Task. ALREADY_EXIST");
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        taskRepository.save(task);
    }
    public @NotNull List<Task> findAll() {
        log.debug("TaskService: Find all tasks");
        return taskRepository.findAll();
    }
    public void deleteByName(@NotNull String taskName) {
        log.debug("TaskService: Delete a task");
        taskRepository.deleteByName(taskName);
    }
    public @NotNull Task findByName(@NotNull String name) {
        log.debug(String.format("TaskService: Finding a task by name-%s", name));
        return taskRepository.findByName(name).orElseThrow(
                () -> {
                    log.error(String.format("TaskService Exception: NOT_FOUND-%s", name));
                    throw  new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name));
                });
    }
    public void update(@NotNull Task task) {
        log.debug(String.format("TaskService: Update a task-%s", task.getName()));
        taskRepository.findByName(task.getName()).orElseThrow(
                () -> {
                    log.error(String.format("TaskService Exception: NOT_FOUND-%s",
                            task.getName()));
                    throw new EntityNotFoundException(
                            String.format(ENTITY_NOT_FOUND, task.getName()));
                });
        taskRepository.update(task);
    }
}
