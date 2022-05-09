package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.persistence.UserDataBaseRepository;

public class UserDataBaseService {
    private final UserDataBaseRepository userDataBaseRepository;
    public UserDataBaseService(UserDataBaseRepository userDataBaseRepository) {
        this.userDataBaseRepository = userDataBaseRepository;
    }
    public void createUser(final User user) {
        userDataBaseRepository.save(user);
    }
    public List<User> findAll() {
        return userDataBaseRepository.findAll();
    }
    public User findUserByName(final String username) {
        return userDataBaseRepository.findUser(username);
    }
}
