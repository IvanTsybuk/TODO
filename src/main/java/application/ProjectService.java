package application;

import domain.Project;
import persistence.ProjectRepository;

import java.util.List;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Integer projectKey, Project project) {
        projectRepository.save(project, projectKey);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


}
