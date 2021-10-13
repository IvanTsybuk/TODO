package org.based.persistence;
import lombok.Getter;
import org.based.domain.Project;
import java.util.*;

public class ProjectRepository {

    @Getter
    private final Map<String, Project> projects = new HashMap<>();
    JsonWriter jsonWriter;

    public ProjectRepository(JsonWriter jsonWriter) {
        this.jsonWriter = jsonWriter;
    }

    public void save(Project project) {
        projects.put(project.getName(), project);
    }

    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    public void writeInfo(Map<String, Project>map){
        jsonWriter.writeProject(map);
    }
}
