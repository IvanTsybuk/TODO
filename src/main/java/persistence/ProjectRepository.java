package persistence;

import domain.Project;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ProjectRepository {

    private final Map<Project, String> projects= new HashMap();


    public void addProject(Project project, String str){
        getProjects().put(project, str);
    }

    public String showAllProjects(){
        return getProjects().toString();
    }
}






