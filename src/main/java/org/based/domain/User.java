package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String name;
    private String userSurName;

    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("userSurName") String userSurName) {
        this.name = name;
        this.userSurName = userSurName;
    }
}
