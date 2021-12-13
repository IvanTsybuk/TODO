package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlWriter<T>  extends AbstractWriter<T> {
    private final File configFile;
    public XmlWriter(String environmentVariable, ObjectMapper objectMapper) {
        super(environmentVariable, objectMapper);
        configFile = getFile(environmentVariable);
    }
    @Override
    @SneakyThrows
    public void writeToFile(Map<String, T> mapToFile) {
        objectMapper.writeValue(configFile, mapToFile);
    }
    @Override
    @SneakyThrows
    public Map<String,T> readFile(TypeReference<HashMap<String,T>> typeReference) {
        if(configFile.length()==0){
            setFileStructure(configFile, FileAppend.XML.getAppendType());
        }
        return  objectMapper.convertValue(readTree(configFile), typeReference);
    }
}
