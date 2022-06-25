package org.based.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.File;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class JacksonWriter<T> implements Writer<T> {
    @NotNull
    private final ObjectMapper jacksonMapper;
    @NotNull
    private final File configuredFile;
    @NotNull
    private final Class<T> typeClass;
    public JacksonWriter(@NotNull ObjectMapper jacksonMapper,
                         @NotNull File configuredFile,
                         @NotNull Class<T> type) {
        log.info("JacksonWriter initialization");
        this.jacksonMapper = jacksonMapper;
        this.configuredFile = configuredFile;
        this.typeClass = type;
    }
    @Override
    @SneakyThrows
    public void writeToFile(@NotNull Map<String, T> map) {
        log.debug(String.format("JacksonWriter: write to file-%s", configuredFile.getName()));
        jacksonMapper.writeValue(configuredFile, map);
    }
    @Override
    @SneakyThrows
    public @NotNull Map<String, T> readFile() {
        log.debug(String.format("JacksonWriter: read file-%s", configuredFile.getName()));
        final MapType mapType = jacksonMapper.getTypeFactory().constructMapType(Map.class,
                String.class, typeClass);
        log.debug(String.format("JacksonWriter: mapType set-%s", mapType));
        return jacksonMapper.readValue(configuredFile, mapType);
    }
}
