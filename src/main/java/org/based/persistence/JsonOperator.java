package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonOperator extends AbstractWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    void writeToFile(File file, Map<?, ?> mapToFile) {
        objectMapper.writeValue(file, mapToFile);
    }
    @Override
    @SneakyThrows
    Map readFile(File file) {
        return objectMapper.readValue(file, HashMap.class);
    }
}
