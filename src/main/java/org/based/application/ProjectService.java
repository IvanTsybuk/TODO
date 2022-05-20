package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.persistence.RepositoryInterface;

public class ProjectService {
    private final RepositoryInterface<Project> jdbcProjectRepository;
    public ProjectService(RepositoryInterface<Project> jdbcProjectRepository) {
        this.jdbcProjectRepository = jdbcProjectRepository;
    }
    public void createProject(final Project project) {
        jdbcProjectRepository.save(project);
    }
    public List<Project> findAll() {
        return jdbcProjectRepository.findAll();
    }
}
