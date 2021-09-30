package application;

import domain.Task;
import persistence.TaskRepository;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public void addTask(Integer taskKey, Task task) {
        taskRepository.save(taskKey, task);
    }

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    public void removeSelectedTask(String taskName) {

        taskRepository.delete(taskName);

    }
}

