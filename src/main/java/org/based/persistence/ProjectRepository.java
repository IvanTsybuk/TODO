package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.based.domain.Project;
import java.util.*;

public class ProjectRepository {
    @Getter
    @JsonDeserialize(using = BodyDeserializer.class)
    private final Map<String, Project> projects;
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator = new FileOperator();
    private final JsonOperator jsonOperator = new JsonOperator();
    private final TypeReference <HashMap<String, Project>> typeReference = new TypeReference<>() {};

    public ProjectRepository() {
//        projects = setProjectsMapJson();
        projects = setProjectMapXml();
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

    public Map<String,Project> setProjectsMapJson() {
        return jsonOperator.readFile(fileOperator.getFileProjectsJson(), typeReference);
    }

    public Map setProjectMapXml() {
        return xmlOperator.readFile(fileOperator.getFileProjectsXml(), typeReference);
    }
}
