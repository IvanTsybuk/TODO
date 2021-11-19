package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.User;

import java.util.*;

public class UserRepository extends Repository<User> {

    public UserRepository(FileOperator fileOperator) {
        super(fileOperator, new TypeReference<HashMap<String, User>>() {});
    }

    @Override
    protected String getDefaultFileRepositoryPath() {
        return fileOperator.getUSER_DEFAULT_FILE_JSON();
    }
    public void save(User user) {
      repositoryMap.put(user.getUserSurName(), user);
    }
    public List<User> findAll() {
        return new ArrayList<>(repositoryMap.values());
    }
    public User findByName(String surName){
        return repositoryMap.get(surName);
    }
}
