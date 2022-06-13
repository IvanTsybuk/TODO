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
public class User implements Entity {
    private long id;
    private String name;
    private String surname;
    @JsonCreator
    public User(@JsonProperty("name") String name,
                @JsonProperty("surname") String surname) {
        this.name = name;
        this.surname = surname;
    }
}
