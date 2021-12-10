package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class XmlWriter extends AbstractWriter {
    private final XmlMapper xmlMapper = new XmlMapper();
    private final File configFile;
    public XmlWriter(String environmentVariable) {
        super(environmentVariable);
        configFile = getFile(environmentVariable);
    }
    @Override
    @SneakyThrows
    protected void setFileStructure(File defaultFile) {
                FileWriter writer = new FileWriter(defaultFile);
                writer.append("<HashMap></HashMap>");
                writer.flush();
                writer.close();
            }
    @Override
    @SneakyThrows
    public void writeToFile(Map<?, ?> mapToFile) {
        xmlMapper.writeValue(configFile, mapToFile);
    }
    @Override
    @SneakyThrows
    public Map<?,?> readFile(TypeReference<?>typeReference) {
        if (configFile.length() == 0) {
            setFileStructure(configFile);
        }
            JsonNode jsonNodeXML = xmlMapper.readTree(configFile);
        return (Map) xmlMapper.convertValue(jsonNodeXML, typeReference);
    }
}
