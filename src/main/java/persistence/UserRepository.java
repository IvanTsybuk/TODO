package persistence;

import domain.User;

import lombok.Data;
import lombok.Getter;

import java.util.*;

@Getter
public class UserRepository {

    @Getter
    private final Map<String, User> users = new HashMap<>();

}

