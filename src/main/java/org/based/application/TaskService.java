package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements EntityService<Task> {
    private final Repository<Task> taskRepository;
    public TaskService(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public void save(final Task task) {
        taskRepository.save(task);
    }
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    @Override
    public void deleteByName(String taskName) {
        taskRepository.deleteByName(taskName);
    }
    @Override
    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }
}
