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

    public void addTask(Task task){

      getTaskRepository().getTaskList().add(task);
    }

    public String getTaskList(){
       return  getTaskRepository().getTaskList().toString();
    }

    public void removeSelectedTask(String str){

        getTaskRepository().getTaskList().removeIf(task -> task.getName().equals(str));

    }
}

