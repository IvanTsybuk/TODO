package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

    @Data
public class Project {
    private String name;
    private String description;

    @JsonCreator
        public Project(@JsonProperty("name") String name,@JsonProperty("description") String description) {
            this.name = name;
            this.description = description;
        }
    }
