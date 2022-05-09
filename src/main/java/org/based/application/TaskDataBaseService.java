package org.based.application;

import java.util.List;
import org.based.domain.Task;
import org.based.persistence.TaskDataBaseRepository;

public class TaskDataBaseService {
    private final TaskDataBaseRepository taskDataBaseRepository;
    public TaskDataBaseService(TaskDataBaseRepository taskDataBaseRepository) {
        this.taskDataBaseRepository = taskDataBaseRepository;
    }
    public void createTask(final Task task) {
        taskDataBaseRepository.save(task);
    }
    public List<Task> findAll() {
        return taskDataBaseRepository.findAll();
    }
    public void removeByName(String taskName) {
        taskDataBaseRepository.delete(taskName);
    }
}
