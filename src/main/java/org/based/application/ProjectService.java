package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService implements EntityService<Project> {
    private final Repository<Project> projectRepository;
    public ProjectService(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }
    @Override
    public void save(final Project project) {
        projectRepository.save(project);
    }
    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    @Override
    public void deleteByName(String name) {
        projectRepository.deleteByName(name);
    }
    @Override
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
}
