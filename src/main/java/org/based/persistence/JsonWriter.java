package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class JsonWriter {

    private final FileOperator fileOperator = new FileOperator();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public JsonWriter(ProjectRepository projectRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @SneakyThrows
    public void writeProject(){
        objectMapper.writeValue(fileOperator.getFileProjects(), projectRepository.findAll());
    }

    @SneakyThrows
    public void writeTask(){
        objectMapper.writeValue(fileOperator.getFileTasks(),taskRepository.findAll());
    }

    @SneakyThrows
    public void writeUser(){
        objectMapper.writeValue(fileOperator.getFileUsers(), userRepository.findAll());
    }
}
