package org.based.persistence;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.util.HashMap;
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
    Map readFile(File file, TypeReference<?>typeReference) {

//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode jsonNodeXML = xmlMapper.readTree(file);

        return (Map) xmlMapper.convertValue(jsonNodeXML, typeReference);
//                XmlMapper().readerFor(new TypeReference<HashMap>() {
//        }).readValue(file);
//                (Map) xmlMapper.readValue(file, typeReference);
    }
}
