package org.based.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import org.based.domain.Project;

import java.io.File;
import java.util.*;

public class ProjectRepository extends Repository<Project> {
    private final String PROJECT_DEFAULT_FILE_JSON = "target/projectsII.json";

//    private final Map<String, Project> projects;
//    private final FileOperator fileOperator;

    public ProjectRepository(FileOperator fileOperator) {
            super(fileOperator, new TypeReference<HashMap<String, Project>>() {
            });
//        this.fileOperator = fileOperator;
//        projects = setProjectRep();
    }

    public void save(Project project) {
//        projects.put(project.getName(), project);
        repositoryMap.put(project.getName(), project);
    }
    public List<Project> findAll() {
//        return new ArrayList<>(projects.values());
        return new ArrayList<>(repositoryMap.values());
    }

    @Override
    protected String getDefaultFileRepositoryPath() {
        return fileOperator.getPROJECT_DEFAULT_FILE_JSON();
    }


    //    public Map<String, Project> setProjectRep() {
//        final File projectRepositoryFile =
//                fileOperator.setRepositoryFile(fileOperator.getPROJECT_DEFAULT_FILE_XML());
//        if (!fileOperator.verifyConfig()) {
//            switch (fileOperator.getExtension(projectRepositoryFile)) {
//                case "json":
//                    return readJsonMap(projectRepositoryFile);
//                case "xml":
//                    return readXmlMap(projectRepositoryFile);
//            }
//        }
//        return new HashMap<>();
//    }
//    private Map readJsonMap(File repositoryFile) {
//        return jsonOperator.readFile(repositoryFile, typeReference);
//    }
//    private Map readXmlMap(File repositoryFile) {
//        return xmlOperator.readFile(repositoryFile, typeReference);
//    }
//    public void writeProject() {
//        final File projectRepositoryFile =
//                fileOperator.setRepositoryFile(fileOperator.getPROJECT_DEFAULT_FILE_XML());
//        if (!fileOperator.verifyConfig()) {
//            switch (fileOperator.getExtension(projectRepositoryFile)) {
//                case "json":
//                    writeJson(projectRepositoryFile);
//                    break;
//                case "xml":
//                    writeMapXml(projectRepositoryFile);
//
//                    break;
//            }
//            if (fileOperator.verifyConfig()) {
//                writeJson(projectRepositoryFile);
//            }
//        }
//    }
//    private void writeMapXml(File taskRepositoryFile) {
//        xmlOperator.writeToFile(taskRepositoryFile, projects);
//    }
//    private void writeJson(File taskRepositoryFile) {
//        jsonOperator.writeToFile(taskRepositoryFile, projects);
//    }
}
