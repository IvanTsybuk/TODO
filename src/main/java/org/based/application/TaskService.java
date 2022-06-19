package org.based.application;
import java.util.List;
import org.based.domain.Task;
import org.based.exceptions.EntityAlreadyExistsException;
import org.based.exceptions.EntityNotFoundException;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private static final String ALREADY_EXIST = "Task with name - %s, is already exist";
    private static final String ENTITY_NOT_FOUND = "Task with name - %s, is not found";
    private final Repository<Task> taskRepository;
    public TaskService(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }
    public void save(final Task task) {
        taskRepository.findByName(task.getName())
                .ifPresent(a -> {
                    throw new EntityAlreadyExistsException(
                            String.format(ALREADY_EXIST, a.getName()));
                });
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
        taskRepository.findByName(task.getName()).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format(ENTITY_NOT_FOUND, task.getName())));
        taskRepository.update(task);
    }
}
