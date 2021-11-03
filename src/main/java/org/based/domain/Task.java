package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;

@Data
public class Task {
    @Getter
    private String name;
    private String description;

    @JsonCreator
    public Task(@JsonProperty("name") String name,
                @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}

//WORKS
//    @JsonCreator
//    public Task(@JsonProperty("name")  String name,
//                @JsonProperty("description") String description) {
//        this.name = name;
//        this.description = description;
