package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class JsonWriter extends AbstractWriter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File configFile;
    public JsonWriter(String configurationFilePath) {
        super(configurationFilePath);
        configFile = getFile(configurationFilePath);
    }
    @Override
    protected File getFile(String configurationFilePath) {
        return super.getFile(configurationFilePath);
    }
    @Override
    @SneakyThrows
    void setFileStructure(File defaultFile) {
                FileWriter writer = new FileWriter(defaultFile);
                writer.append("{}");
                writer.flush();
                writer.close();
        }
    @Override
    @SneakyThrows
    public void writeToFile(Map<?, ?> mapToFile) {
        objectMapper.writeValue(configFile, mapToFile);
    }
    @Override
    @SneakyThrows
    public Map<?,?> readFile(TypeReference <?> typeReference) {
        if(configFile.length()==0){
            setFileStructure(configFile);
        }
        JsonNode jsonNode = objectMapper.readTree(configFile);
        return (Map) objectMapper.convertValue(jsonNode, typeReference);
    }
}
