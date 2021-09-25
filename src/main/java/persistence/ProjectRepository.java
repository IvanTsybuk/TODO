package persistence;

import domain.Project;
import lombok.Data;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Data
public class ProjectRepository {

    private final Map<Project,String> projects= new HashMap();

    public void addProject(Project project, String projectStatus){
        getProjects().put(project, projectStatus);
    }

    public List<String> showAllProjects(){

        return projects.values().stream().collect(Collectors.toList());
    }
}






