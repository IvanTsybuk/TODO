package org.based.persistence;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Map;

public class BodyDeserializer extends JsonDeserializer {
    @Override
    public Map<String, Object> deserialize(JsonParser jsonParser, DeserializationContext dc) throws IOException, JacksonException {
        JsonDeserializer <Object> deserializer = dc.findRootValueDeserializer(dc.constructType(Map.class));
        Map<String, Object> map = (Map<String, Object>) deserializer.deserialize(jsonParser, dc);
        return map;

    }
}
