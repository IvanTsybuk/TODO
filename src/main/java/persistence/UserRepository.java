package persistence;

import domain.User;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserRepository {

    private final Map<String, User> users = new HashMap<>();

}

