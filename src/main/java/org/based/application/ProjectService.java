package org.based.application;

import lombok.Getter;
import org.based.domain.Project;
import org.based.persistence.Repository;

import java.util.List;

public class ProjectService {
    @Getter
    private final Repository<Project>repository;

    public ProjectService(Repository<Project>repository) {
        this.repository = repository;
    }

    public void createProject(Project project) {
        repository.save(project);
    }

    public List<Project> getAllProjects() {
        return repository.findAll();
    }
}
