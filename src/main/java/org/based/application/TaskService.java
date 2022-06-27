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
        log.debug(String.format("Save new task: %s", task));
        taskRepository.findByName(task.getName())
                .ifPresent(a -> {
                    log.error(String.format("Save Exception-task:%s. ALREADY_EXIST", task));
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
        taskRepository.save(task);
    }
    @NotNull
    public List<Task> findAll() {
        log.debug("Find all tasks");
        return taskRepository.findAll();
    }
    public void deleteByName(@NotNull final String taskName) {
        log.debug(String.format("delete task by name:%s", taskName));
        taskRepository.deleteByName(taskName);
    }
    @NotNull
    public Task findByName(@NotNull final String name) {
        log.debug(String.format("find task by name: %s", name));
        return taskRepository.findByName(name).orElseThrow(
                () -> {
                    log.error(String.format("findByName Exception. NOT_FOUND task name: %s", name));
                    throw  new EntityNotFoundException(String.format(ENTITY_NOT_FOUND, name));
                });
    }
    public void update(@NotNull final Task task) {
        log.debug(String.format("Update task: %s", task));
        taskRepository.findByName(task.getName()).orElseThrow(
                () -> {
                    log.error(String.format("update Exception. NOT_FOUND task name:%s",
                            task.getName()));
                    throw new EntityNotFoundException(
                            String.format(ENTITY_NOT_FOUND, task.getName()));
                });
        taskRepository.update(task);
    }
}
