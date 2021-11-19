package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskRepository extends Repository<Task> {

    public TaskRepository(FileOperator fileOperator) {
        super(fileOperator, new TypeReference<HashMap<String, Task>>() {});
    }
    @Override
    protected String getDefaultFileRepositoryPath() {
        return fileOperator.getTASK_DEFAULT_FILE_XML();
    }
    public void save(Task task) {
        final String name = task.getName();
        repositoryMap.put(name, task);
    }
    public List<Task> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
    public void delete(String name) {
        repositoryMap.values().removeIf(task -> task.getName().equals(name));
    }
}
