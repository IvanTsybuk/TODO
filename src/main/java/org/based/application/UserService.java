package org.based.application;
import org.based.domain.User;
import org.based.persistence.Repository;
import java.util.List;

public class UserService {

    private final Repository<User>repository;

    public UserService(Repository repository) {
        this.repository = repository;
    }

    public void createNewUser(User user) {
        repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User findUserBySurName(String userSurname) {
        return repository.findByName(userSurname);
    }
}
