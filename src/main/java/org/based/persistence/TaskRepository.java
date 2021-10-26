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
    private final FileOperator fileOperator = new FileOperator();

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
       jsonOperator.writeToFile(fileOperator.getFileTasksJSon(), taskList);
    }
    public void writeTaskListToXml(){
        xmlOperator.writeToFile(fileOperator.getFileTasksXml(), taskList);
    }

    public Map<String, Task> readTaskFromJson(){
        return new HashMap<>(jsonOperator.readFile(fileOperator.getFileTasksJSon()));
    }
    public Map<String, Task> readTaskFromXml(){
        return new HashMap(xmlOperator.readFile(fileOperator.getFileTasksJSon()));
    }
}
