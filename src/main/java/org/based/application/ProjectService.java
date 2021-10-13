package org.based.application;

import lombok.Getter;
import org.based.domain.Project;
import org.based.persistence.ProjectRepository;

import java.util.List;
import java.util.Map;

public class ProjectService {
    @Getter
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project) {
        projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
