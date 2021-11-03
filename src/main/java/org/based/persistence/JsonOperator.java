package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
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
    Map readFile(File file, TypeReference <?> typeReference) {
        JsonNode jsonNode = objectMapper.readTree(file);

        return (Map) objectMapper.convertValue(jsonNode, typeReference);
    }
}

// Works
//@Override
//@SneakyThrows
//void writeToFile(File file, Map<?, ?> mapToFile) {
//    objectMapper.writeValue(file, mapToFile);
//}
//    @Override
//    @SneakyThrows
//    Map readFile(File file, TypeReference <?> typeReference) {
//        JsonNode jsonNode = objectMapper.readTree(file);
//        return (Map) objectMapper.convertValue(jsonNode, typeReference);
//    }
//}
