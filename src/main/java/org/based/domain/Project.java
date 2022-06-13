package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.based.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project implements Entity {
    private long id;
    private String name;
    private String description;
    @JsonCreator
    public Project(@JsonProperty("name") String name,
                   @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
