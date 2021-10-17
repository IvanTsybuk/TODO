package org.based.persistence;

import lombok.Getter;
import org.based.domain.Project;
import java.util.*;

public class ProjectRepository {
    @Getter
    private final Map<String, Project> projects;
    private final JsonWriter jsonWriter = new JsonWriter();

    public ProjectRepository() {
        projects = setProjectsMapJson();
    }


    public void save(Project project) {
        projects.put(project.getName(), project);
    }

    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    public void writeProjectsMapJson(Map<String, Project>map){
        jsonWriter.writeProjectJson(map);
    }

    public Map<String, Project> setProjectsMapJson(){
        Map<String, Project> readJsonFileMap = jsonWriter.readJsonProject();
        return new HashMap<>(readJsonFileMap);
    }

}
