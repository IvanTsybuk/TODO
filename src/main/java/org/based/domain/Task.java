package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import org.based.persistence.Entity;
import org.based.persistence.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NonNull
public class Task  {
    @Getter
    private String name;
    private String description;

    @JsonCreator
    public Task(@JsonProperty("name") String name, @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}
