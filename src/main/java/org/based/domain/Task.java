package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.based.persistence.Entity;

@Data
@NoArgsConstructor
public class Task implements Entity {
    private String name;
    private String description;
    @JsonCreator
    public Task(@JsonProperty("name") String name,
                @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
