package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Data
public abstract class AbstractWriter {
    protected final String configurationFilePath;
    public AbstractWriter(String configurationFilePath) {
        this.configurationFilePath = configurationFilePath;
    }
    @SneakyThrows
    protected File getFile(String configurationFilePath) {
        File file = new File(configurationFilePath);
        if (!file.exists()) {
            final File defaultFile = new File(configurationFilePath);
            defaultFile.createNewFile();
            if(defaultFile.length()==0){
                setFileStructure(defaultFile);
            }
            return defaultFile;
        }
        return file;
    }
    abstract void setFileStructure(File defaultFile);
    @SneakyThrows
    protected void writeToFile (Map < ?, ?>mapToFile){
    }
    protected Map<?, ?> readFile (TypeReference <?>typeReference){
        return new HashMap<>();
    }
    }
