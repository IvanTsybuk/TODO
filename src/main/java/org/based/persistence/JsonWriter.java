package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class JsonWriter<T> extends AbstractWriter<T> {
    private final File configFile;
    public JsonWriter(String configurationFilePath, ObjectMapper objectMapper) {
        super(configurationFilePath, objectMapper);
        configFile = getFile(configurationFilePath);
    }
    @Override
    protected File getFile(String configurationFilePath) {
        return super.getFile(configurationFilePath);
    }
    @Override
    @SneakyThrows
    public void writeToFile(Map<String,T> mapToFile) {
        objectMapper.writeValue(configFile, mapToFile);
    }
    @SneakyThrows
    public Map<String,T> readFile(TypeReference<HashMap<String,T>> typeReference) {
        if(configFile.length()==0){
            setFileStructure(configFile, FileAppend.JSON.getAppendType());
        }
        return   objectMapper.convertValue(readTree(configFile),typeReference);
    }
}
