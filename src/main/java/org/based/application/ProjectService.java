package org.based.application;

import org.based.domain.Project;
import org.based.persistence.Repository;

import java.util.List;

public class ProjectService {
    private final Repository<Project> repository;
    public ProjectService(Repository<Project> repository) {
        this.repository = repository;
    }
    public void createProject(Project project) {
        repository.save(project);
    }
    public List<Project> getProjects() {
        return repository.findAll();
    }
}
