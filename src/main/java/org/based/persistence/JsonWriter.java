package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.based.domain.Project;

import java.util.HashMap;
import java.util.Map;

public class JsonWriter {

    private final FileOperator fileOperator = new FileOperator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @NonNull
    public void writeProjectJson(Map<String, Project>projectMapJson){
        objectMapper.writeValue(fileOperator.getFileProjects(), projectMapJson);
    }

    @SneakyThrows
    public Map<String,Project> readJsonProject() {
        return  new ObjectMapper().readValue(fileOperator.getFileProjects(), HashMap.class);
    }
}
