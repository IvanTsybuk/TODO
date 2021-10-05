package org.based.application;

import org.based.domain.Task;
import org.based.persistence.TaskRepository;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public void removeSelectedTask(String taskName) {
        taskRepository.delete(taskName);
    }
}
