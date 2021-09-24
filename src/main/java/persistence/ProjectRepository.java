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
    private final List<String>targetList = new ArrayList<>();



    public void addProject(Project project, String str){
        getProjects().put(project, str);
    }

    public List showAllProjects(){

        List<String> collect = projects.values().stream().collect(Collectors.toList());
        List<Project> keys=projects.keySet().stream().collect(Collectors.toList());

        return List.of(keys, collect);
    }
}






