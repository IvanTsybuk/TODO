package org.based.application;

import org.based.domain.User;
import org.based.persistence.Repository;
import java.util.List;

public class UserService {
    private final Repository<User>repository;
    public UserService(Repository repository) {
        this.repository = repository;
    }
    public void createUser(User user){
        repository.save(user);
    }
    public List<User> getUsers() {
        return repository.findAll();
    }
    public User findUserByName(String username) {
        return repository.findByName(username);
    }
}
