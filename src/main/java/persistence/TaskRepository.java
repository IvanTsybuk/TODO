package persistence;

import domain.Task;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TaskRepository {

    private final List<Task> taskList = new ArrayList<>();

    public void saveTask(Task task) {
        getTaskList().add(task);
    }

    public List<Task> toStringTaskList() {

        return taskList.stream().collect(Collectors.toList());
    }

    public void deleteTaskFromList(String str){
        getTaskList().removeIf(task -> task.getName().equals(str));
    }

}
