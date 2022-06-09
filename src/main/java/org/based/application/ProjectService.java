package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.persistence.Repository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final Repository<Project> projectRepository;
    public ProjectService(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }
    public void save(final Project project) {
        projectRepository.save(project);
    }
    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public void deleteByName(String name) {
        projectRepository.deleteByName(name);
    }
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
    public void update(Project project) {
        projectRepository.update(project);
    }
}
