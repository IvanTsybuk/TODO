package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.persistence.Repository;

public class UserService {
    private final Repository<User> repository;
    public UserService(final Repository<User> repository) {
        this.repository = repository;
    }
    public void createUser(final User user) {
        repository.save(user);
    }
    public List<User> findAll() {
        return repository.findAll();
    }
    public User findUserByName(final String username) {
        return repository.findByName(username);
    }
}
