package org.based.persistence;
import org.based.domain.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private final Map<String, Task> taskList;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator= new XmlOperator();

    public TaskRepository() {
        taskList = readTaskFromJson();
    }

    public void save(Task task) {
       taskList.put(task.getName(), task);
    }
    public List<Task> findAll() {
        return new ArrayList<>(taskList.values());
    }
    public void delete(String name){
        taskList.values().removeIf(task -> task.getName().equals(name));
    }
    public void writeTaskMapToJson(){
       jsonOperator.writeToFile(taskList);
    }
    public void writeTaskListToXml(){
        xmlOperator.writeToFile(taskList);
    }

    public Map <String, Task>readTaskFromJson(){


        return new HashMap <String, Task>(jsonOperator.readFile());
    }
    public Map <String, Task>readTaskFromXml(){
        return new HashMap <String, Task>(xmlOperator.readFile());
    }
}
