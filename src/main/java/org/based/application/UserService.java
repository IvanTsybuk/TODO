package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Repository<User> userRepository;
    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }
    public void save(final User user) {
        userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findByName(final String username) {
        return userRepository.findByName(username);
    }
    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }
    public void update(User user) {
        userRepository.update(user);
    }
}
