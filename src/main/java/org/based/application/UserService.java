package org.based.application;

import java.util.List;
import org.based.domain.User;
import org.based.persistence.RepositoryInterface;

public class UserService {
    private final RepositoryInterface<User> jdbcUserRepository;
    public UserService(RepositoryInterface<User> jdbcUserRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
    }
    public void createUser(final User user) {
        jdbcUserRepository.save(user);
    }
    public List<User> findAll() {
        return jdbcUserRepository.findAll();
    }
    public User findUser(final String username) {
        return jdbcUserRepository.findByName(username);
    }
}
