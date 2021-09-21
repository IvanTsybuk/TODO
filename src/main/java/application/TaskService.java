package application;

import domain.Task;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@NonNull
public class TaskService {
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task){

        taskList.add(task);
    }

    public String getTaskList(){
       return  taskList.toString();
    }

    public void removeSelectedTask(String str){

        taskList.removeIf(task -> task.getName().equals(str));

    }
}

