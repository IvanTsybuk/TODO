package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;

public class JacksonWriter<T> implements Writer<T> {
    private final ObjectMapper jacksonMapper;
    private final File configuredFile;
    private final T typeClass;
    public JacksonWriter(ObjectMapper jacksonMapper, File configuredFile, T typeClass) {
        this.jacksonMapper = jacksonMapper;
        this.configuredFile = configuredFile;
        this.typeClass = typeClass;
    }
    @Override
    @SneakyThrows
    public void writeToFile(Map<String, T> map) {
        jacksonMapper.writeValue(configuredFile, map);
    }
    @Override
    @SneakyThrows
    public Map<String, T> readFile() {
        final MapType mapType = jacksonMapper.getTypeFactory().constructMapType(Map.class, String.class, (Class<?>) typeClass);
        return jacksonMapper.readValue(configuredFile, mapType);
    }
}
