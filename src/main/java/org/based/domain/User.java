package org.based.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String userName;
    private String userSurName;

    @JsonCreator
    public User(@JsonProperty("userName") String userName,@JsonProperty("userSurName") String userSurName) {
        this.userName = userName;
        this.userSurName = userSurName;
    }
}
