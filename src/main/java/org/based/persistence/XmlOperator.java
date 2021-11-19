package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Map;

public class XmlOperator extends AbstractWriter {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    @SneakyThrows
    void writeToFile(File file, Map <?, ?> mapToFile) {
        xmlMapper.writeValue(file, mapToFile);
    }

    @Override
    @SneakyThrows
    Map<?,?> readFile(File file, TypeReference<?>typeReference) {
        JsonNode jsonNodeXML = xmlMapper.readTree(file);
        return (Map) xmlMapper.convertValue(jsonNodeXML, typeReference);
    }
}
