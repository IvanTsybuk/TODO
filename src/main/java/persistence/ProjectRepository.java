package persistence;

import domain.Project;

import java.util.*;

public class ProjectRepository {

    private final Map<String,Project> projects= new HashMap();

    public void save(Project project, String projectStatus){
       projects.put(projectStatus, project);
    }

    public List<Project> findAll(){

        return new ArrayList<>(projects.values());
    }
}






