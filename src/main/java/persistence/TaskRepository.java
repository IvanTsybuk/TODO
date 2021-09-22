package persistence;

import domain.Task;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskRepository {

    private final List<Task> taskList = new ArrayList<>();

    public void saveTask(Task task) {
        getTaskList().add(task);
    }

    public String toStringTaskList() {

        return getTaskList().toString();
    }

    public void deleteTaskFromList(String str){
        getTaskList().removeIf(task -> task.getName().equals(str));
    }

}
