package org.based.application;

import java.util.List;
import org.based.domain.Project;
import org.based.persistence.ProjectDataBaseRepository;

public class ProjectDataBaseService {
    private final ProjectDataBaseRepository projectDataBaseRepository;
    public ProjectDataBaseService(ProjectDataBaseRepository projectDataBaseRepository) {
        this.projectDataBaseRepository = projectDataBaseRepository;
    }
    public void createProject(final Project project) {
        projectDataBaseRepository.save(project);
    }
    public List<Project> getProjects() {
        return projectDataBaseRepository.findAll();
    }
}
