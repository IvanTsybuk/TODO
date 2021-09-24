package application;

import domain.Project;

import lombok.Data;
import persistence.ProjectRepository;

import java.util.List;

@Data
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project, String str) {
        projectRepository.addProject(project, str);


    }

    public List showProjects() {

        return projectRepository.showAllProjects();
    }
}
