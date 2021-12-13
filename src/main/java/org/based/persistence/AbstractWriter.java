package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractWriter<T> {
    protected final String configurationFilePath;
    protected final ObjectMapper objectMapper;
    public AbstractWriter(String configurationFilePath, ObjectMapper objectMapper) {
        this.configurationFilePath = configurationFilePath;
        this.objectMapper = objectMapper;
    }
    @SneakyThrows
    protected File getFile(String configurationFilePath) {
        File file = new File(configurationFilePath);
        if (!file.exists()) {
            final File defaultFile = new File(configurationFilePath);
            defaultFile.createNewFile();
            if (defaultFile.length() == 0) {
                setFileStructure(defaultFile, FileAppend.JSON.getAppendType());
            }
            return defaultFile;
        }
        return file;
    }
    @SneakyThrows
    protected void setFileStructure(File defaultFile, String appendString) {
        FileWriter writer = new FileWriter(defaultFile);
        writer.append(appendString);
        writer.flush();
        writer.close();
    }
    @SneakyThrows
    protected JsonNode readTree(File file) {
        return objectMapper.readTree(file);
    }
    public abstract void writeToFile(Map<String, T> map);
    public abstract Map<String, T> readFile(TypeReference<HashMap<String, T>> typeReference);
}
