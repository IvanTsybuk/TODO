package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.based.domain.Project;

import java.util.Map;

public class JsonWriter {

    private final FileOperator fileOperator = new FileOperator();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @NonNull
    public void writeProject(Map<String, Project>map){
        objectMapper.writeValue(fileOperator.getFileProjects(), map);
    }
}
