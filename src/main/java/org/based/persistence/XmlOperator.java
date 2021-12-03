package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

public class XmlOperator extends AbstractOperator {
    private final XmlMapper xmlMapper = new XmlMapper();

    public XmlOperator(File fileConfigPath) {
        super(fileConfigPath);
    }

    @Override
    @SneakyThrows
    public void writeToFile(Map<?, ?> mapToFile) {
        xmlMapper.writeValue(getFileConfigPath(), mapToFile);
    }
    @Override
    @SneakyThrows
    public Map<?,?> readFile(TypeReference<?>typeReference) {
        JsonNode jsonNodeXML = xmlMapper.readTree(getFileConfigPath());
        return (Map) xmlMapper.convertValue(jsonNodeXML, typeReference);
    }
}
