package org.based.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.based.persistence.FileOperator;
import org.based.persistence.ProjectRepository;

public class JsonWriter {

    private final FileOperator fileOperator = new FileOperator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public void writeProject(ProjectRepository projectRepository){
        objectMapper.writeValue(fileOperator.getFileProjects(), projectRepository.findAll());
    }
}
