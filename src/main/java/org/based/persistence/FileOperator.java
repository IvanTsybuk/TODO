package org.based.persistence;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;

@Getter
public class  FileOperator {
    private final File fileProjectsJson = new File("target/projects.json");
    private final File fileProjectsXml = new File("target/projects.xml");
    private final File fileTasksJson = new File("target/tasks.json");
    private final File fileTasksXml = new File("target/tasks.xml");
    private final File fileUserJson = new File("target/users.json");
    private final File fileUserXml = new File("target/users.xml");

    @SneakyThrows
    public File getFileProjectsJson() {
        if (!fileProjectsJson.exists()){
            fileProjectsJson.createNewFile();}
        return fileProjectsJson;
    }
    @SneakyThrows
    public File getFileProjectsXml() {
        if (!fileProjectsJson.exists()){
            fileProjectsJson.createNewFile();}
        return fileProjectsXml;
    }
    @SneakyThrows
    public File getFileTasksJSon() {
        if (!fileTasksJson.exists()){
            fileTasksJson.createNewFile();}
        return fileTasksJson;
    }
    @SneakyThrows
    public File getFileTasksXml() {
        if (!fileTasksXml.exists()){
            fileTasksXml.createNewFile();}
        return fileTasksXml;
    }
    @SneakyThrows
    public File getFileUserJSon() {
        if (!fileUserJson.exists()){
            fileUserJson.createNewFile();}
        return fileUserJson;
    }
    @SneakyThrows
    public File getFileUserXml() {
        if (!fileUserXml.exists()){
            fileUserXml.createNewFile();}
        return fileUserXml;
    }
}
