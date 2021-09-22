package application;

import domain.Task;
import lombok.Data;
import persistence.TaskRepository;


@Data
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task task) {
        taskRepository.saveTask(task);
    }

    public String getTaskList() {
        return taskRepository.toStringTaskList();
    }

    public void removeSelectedTask(String str) {

        taskRepository.deleteTaskFromList(str);

    }
}

