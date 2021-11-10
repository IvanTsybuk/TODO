package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {

    private final Map<String, Task> taskList;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final XmlOperator xmlOperator= new XmlOperator();
    private final FileOperator fileOperator;
    private final TypeReference<HashMap<String, Task>>typeReference=
            new TypeReference<>() {};

    public TaskRepository(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
        taskList = setTaskRep();
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
    public Map<String, Task> setTaskRep() {
        final File taskRepositoryFile =
                fileOperator.setRepositoryFile(fileOperator.getTaskDefaultFileJson());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(taskRepositoryFile)) {
                case "json":
                    return readJsonMap(taskRepositoryFile);
                case "xml":
                    return readXmlMap(taskRepositoryFile);
            }
        }
        return new HashMap<>();
    }
    private Map readJsonMap(File repositoryFile) {
        return jsonOperator.readFile(repositoryFile, typeReference);
    }
    private Map readXmlMap(File repositoryFile) {
        return xmlOperator.readFile(repositoryFile, typeReference);
    }
    public void writeTask() {
        final File taskRepositoryFile =
                fileOperator.setRepositoryFile(fileOperator.getTaskDefaultFileJson());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(taskRepositoryFile)) {
                case "json":
                    writeTaskJson(taskRepositoryFile);
                    break;
                case "xml":
                    writeTaskMapXml(taskRepositoryFile);
                    break;
            }
            if (fileOperator.verifyConfig()) {
                writeTaskJson(taskRepositoryFile);
            }
        }
    }
    private void writeTaskMapXml(File taskRepositoryFile) {
        xmlOperator.writeToFile(taskRepositoryFile, taskList);
    }
    private void writeTaskJson(File taskRepositoryFile) {
        jsonOperator.writeToFile(taskRepositoryFile, taskList);
    }
}
