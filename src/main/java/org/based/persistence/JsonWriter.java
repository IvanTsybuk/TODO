package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

public class JsonWriter extends AbstractWriter {

    private final ObjectMapper objectMapper = new ObjectMapper();

        public JsonWriter(String environmentVariable, Class clazz) {
        super(environmentVariable, clazz);
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
