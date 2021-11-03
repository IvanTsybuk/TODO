package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.based.domain.Project;
import org.based.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JacksonXmlRootElement(namespace = "taskList")
public class TaskRepository {

    @JsonDeserialize(using = BodyDeserializer.class)
    private final Map<String, Task> taskList;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator= new XmlOperator();
    private final FileOperator fileOperator = new FileOperator();
    XmlMapper xmlMapper = new XmlMapper();
    private final TypeReference<HashMap<String, Task>>typeReference=
            new TypeReference<>() {};

    public TaskRepository() {
//        taskList = readTaskFromJson();
        taskList = readTaskFromXml();
    }

    public void save(Task task) {
        final String name = task.getName();
        taskList.put(name, task);
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

    public Map<String, Task> readTaskFromJson()  {
        return  jsonOperator.readFile(fileOperator.getFileTasksJSon(), typeReference);
    }
    @JacksonXmlProperty(namespace = "VXC", localName = "taskList")
    public Map<String, Task> readTaskFromXml(){
        return xmlOperator.readFile(fileOperator.getFileTasksXml(), typeReference);
    }
}
