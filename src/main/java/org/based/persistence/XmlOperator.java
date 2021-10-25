package org.based.persistence;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class XmlOperator extends AbstractWriter {
    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    @SneakyThrows
    void writeToFile(Map <?,?> mapToFile) {
        xmlMapper.writeValue(getXmlProjectFile(), mapToFile);
    }

    @Override
    @SneakyThrows
    Map readFile() {
        return xmlMapper.readValue(getXmlProjectFile(), HashMap.class);
    }
}
