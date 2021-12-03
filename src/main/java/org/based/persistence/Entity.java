package org.based.persistence;

import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;

import java.util.List;

public interface Entity {
    void save(Task task);
    void save(User user);
    void save(Project project);
    List<?> findAll();
    void delete(String str);
    User findByName(String surName);
}