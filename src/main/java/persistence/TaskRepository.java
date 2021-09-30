package persistence;
import domain.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private final Map<Integer, Task> taskList = new HashMap<>();

    public void save(Integer taskKey, Task task) {
       taskList.put(taskKey, task);
    }

    public List<Task> findAll() {
        return new ArrayList<>(taskList.values());
    }

    public void delete(String name){
        taskList.values().removeIf(task -> task.getName().equals(name));
    }

}
