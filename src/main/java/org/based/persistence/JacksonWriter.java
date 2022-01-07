package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.File;
import java.util.Map;
import lombok.SneakyThrows;

public class JacksonWriter<T> implements Writer<T> {
    private final ObjectMapper jacksonMapper;
    private final File configuredFile;
    private final Class<T> typeClass;
    public JacksonWriter(ObjectMapper jacksonMapper, File configuredFile, Class<T> type) {
        this.jacksonMapper = jacksonMapper;
        this.configuredFile = configuredFile;
        this.typeClass = type;
    }
    @Override
    @SneakyThrows
    public void writeToFile(Map<String, T> map) {
        jacksonMapper.writeValue(configuredFile, map);
    }
    @Override
    @SneakyThrows
    public Map<String, T> readFile() {
        final MapType mapType = jacksonMapper.getTypeFactory().constructMapType(Map.class,
                String.class, typeClass);
        return jacksonMapper.readValue(configuredFile, mapType);
    }
}
