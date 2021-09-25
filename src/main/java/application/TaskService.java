package application;

import domain.Task;
import lombok.Data;
import persistence.TaskRepository;

import java.util.List;


@Data
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public void addTask(Task task) {
        taskRepository.saveTask(task);
    }

    public List<Task> getTaskList() {
        return taskRepository.showTaskList();
    }

    public void removeSelectedTask(String taskToDelete) {

        taskRepository.deleteTaskFromList(taskToDelete);

    }
}

