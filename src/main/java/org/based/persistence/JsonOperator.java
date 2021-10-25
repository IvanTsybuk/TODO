package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class JsonOperator extends AbstractWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    void writeToFile(Map<?, ?> mapToFile) {
        objectMapper.writeValue(getJsonProjectFile(), mapToFile);
    }

    @Override
    @SneakyThrows
    Map readFile() {
        return new ObjectMapper().readValue(getJsonProjectFile(), HashMap.class);
    }
}
