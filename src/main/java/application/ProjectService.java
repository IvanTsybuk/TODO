package application;

import domain.Project;
import domain.User;
import lombok.Data;
import persistence.ProjectRepository;

@Data
public class ProjectService {

    User user;
    Project project;
    ProjectRepository projectRepository;


    public ProjectService() {
        projectRepository = new ProjectRepository();
    }

    public void createProject(Project project, String str) {

        projectRepository.getProjects().put(project, str);

    }

    public String showProjects() {

        return projectRepository.getProjects().toString();
    }
}
