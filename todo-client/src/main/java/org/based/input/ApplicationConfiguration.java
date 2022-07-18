package org.based.input;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Log4j2
public class ApplicationConfiguration {
    private static final String PROJECT_DEFAULT = "http://localhost:8080/projects";
    private static final String USER_DEFAULT = "http://localhost:8080/users";
    private static final String TASK_DEFAULT = "http://localhost:8080/tasks";
    private static final String PROJECT = "PROJECTS";
    private static final String USER = "USERS";
    private static final String TASK = "TASKS";
    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return new RestTemplate();
    }
    @Bean
    public HostPath getPath() {
        return new HostPath(
                getEnvVariable(PROJECT, PROJECT_DEFAULT),
                getEnvVariable(USER, USER_DEFAULT),
                getEnvVariable(TASK, TASK_DEFAULT));
    }
    @NotNull
    private String getEnvVariable(@Nullable final String sourceValue,
                                  @NotNull final String defaultValue) {
        String environmentVariable = System.getenv(sourceValue);
        if (environmentVariable == null || environmentVariable.isBlank()) {
            environmentVariable = defaultValue;
        }
        return environmentVariable;
    }
}
