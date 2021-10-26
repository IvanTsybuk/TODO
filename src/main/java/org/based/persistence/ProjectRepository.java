package org.based.persistence;

import lombok.Getter;
import org.based.domain.Project;
import java.util.*;

public class ProjectRepository {
    @Getter
    private final Map<String, Project> projects;
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator = new FileOperator();
    private final JsonOperator jsonOperator = new JsonOperator();

    public ProjectRepository() {
        projects = setProjectsMapJson();
    }

    public void save(Project project) {
        projects.put(project.getName(), project);
    }

    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }

    public void writeProjectsMapJson() {
        jsonOperator.writeToFile(fileOperator.getFileProjectsJson(), projects);
    }

    public void writeProjectsMapXml() {
        xmlOperator.writeToFile(fileOperator.getFileProjectsXml(), projects);
    }

    public Map<String, Project> setProjectsMapJson() {
        return new HashMap<String, Project>(jsonOperator.readFile(fileOperator.getFileProjectsJson()));
    }

    public Map<String, Project> setProjectMapXml() {
        return new HashMap(xmlOperator.readFile(fileOperator.getFileProjectsXml()));
    }
}
