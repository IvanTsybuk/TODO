package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

public class JsonOperator extends AbstractOperator {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonOperator(File fileConfigPath) {
        super(fileConfigPath);
    }

    @Override
    @SneakyThrows
    public void writeToFile(Map<?, ?> mapToFile) {
        objectMapper.writeValue(getFileConfigPath(), mapToFile);
    }
    @Override
    @SneakyThrows
    public Map<?,?> readFile(TypeReference <?> typeReference) {
        JsonNode jsonNode = objectMapper.readTree(getFileConfigPath());
        return (Map) objectMapper.convertValue(jsonNode, typeReference);
    }
}
