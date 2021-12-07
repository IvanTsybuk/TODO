package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.util.Map;

public class XmlWriter extends AbstractWriter {

    private final XmlMapper xmlMapper = new XmlMapper();

        public XmlWriter(String environmentVariable, Class clazz) {
        super(environmentVariable, clazz);
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
