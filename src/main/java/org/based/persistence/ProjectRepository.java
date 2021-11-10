package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.Project;

import java.io.File;
import java.util.*;

public class ProjectRepository {

    private final Map<String, Project> projects;
    private final XmlOperator xmlOperator = new XmlOperator();
    private final FileOperator fileOperator;
    private final JsonOperator jsonOperator = new JsonOperator();
    private final TypeReference<HashMap<String, Project>> typeReference = new TypeReference<>() {
    };

    public ProjectRepository(FileOperator fileOperator) {
        this.fileOperator = fileOperator;
        projects = setProjectRep();
    }

    public void save(Project project) {
        projects.put(project.getName(), project);
    }
    public List<Project> findAll() {
        return new ArrayList<>(projects.values());
    }
    public Map<String, Project> setProjectRep() {
       final File projectRepositoryFile =
               fileOperator.setRepositoryFile(fileOperator.getProjectDefaultFileXml());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(projectRepositoryFile)) {
                case "json":
                    return readJsonMap(projectRepositoryFile);
                case "xml":
                    return readXmlMap(projectRepositoryFile);
            }
        }
        return new HashMap<>();
    }
    private Map readJsonMap(File repositoryFile) {
        return jsonOperator.readFile(repositoryFile, typeReference);
    }
    private Map readXmlMap(File repositoryFile) {
        return xmlOperator.readFile(repositoryFile, typeReference);
    }
    public void writeProject() {
        final File projectRepositoryFile =
                fileOperator.setRepositoryFile(fileOperator.getProjectDefaultFileXml());
        if (!fileOperator.verifyConfig()) {
            switch (fileOperator.getExtensionByGuava(projectRepositoryFile)) {
                case "json":
                    writeProjectsMapJson(projectRepositoryFile);
                    break;
                case "xml":
                    writeProjectsMapXml(projectRepositoryFile);
                    break;
            }
            if (fileOperator.verifyConfig()) {
                writeProjectsMapJson(projectRepositoryFile);
            }
        }
    }
    private void writeProjectsMapXml(File projectRepositoryFile) {
        xmlOperator.writeToFile(projectRepositoryFile, projects);
    }
    private void writeProjectsMapJson(File projectRepositoryFile) {
        jsonOperator.writeToFile(projectRepositoryFile, projects);
    }
}
