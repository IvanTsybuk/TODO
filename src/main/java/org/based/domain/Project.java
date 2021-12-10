package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.based.persistence.Entity;

@Data
public class Project implements Entity {
    private String name;
    private String description;
    @JsonCreator
        public Project(@JsonProperty("name") String name,@JsonProperty("description") String description) {
            this.name = name;
            this.description = description;
        }
}
