package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.persistence.Repository;

public class ProjectService {
    private final Repository<Project> repository;
    public ProjectService(final Repository<Project> repository) {
        this.repository = repository;
    }
    public void createProject(final Project project) {
        repository.save(project);
    }
    public List<Project> getProjects() {
        return repository.findAll();
    }
}
