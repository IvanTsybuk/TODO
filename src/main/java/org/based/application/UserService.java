package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements EntityService<User> {
    private final Repository<User> userRepository;
    public UserService(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void save(final User user) {
        userRepository.save(user);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User findByName(final String username) {
        return userRepository.findByName(username);
    }
    @Override
    public void deleteByName(String name) {
        userRepository.deleteByName(name);
    }
}
