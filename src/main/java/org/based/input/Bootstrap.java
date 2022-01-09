package org.based.input;

import org.based.domain.Project;
import org.based.domain.Task;
import org.based.domain.User;
import org.based.persistence.Writer;

public class Bootstrap {
    public static final String PROJECT_PATH = "PROJECT_PATH";
    public static final String TASK_PATH = "TASK_PATH";
    public static final String USER_PATH = "USER_PATH";

    public static void main(String[] args) {
        final Writer<Project> projectWriter = Writer.<Project>builder()
                .environmentVariable(PROJECT_PATH)
                .useClass(Project.class)
                .build();
        final Writer<Task> taskWriter = Writer.<Task>builder()
                .environmentVariable(TASK_PATH)
                .useClass(Task.class)
                .build();
        final Writer<User> userWriter = Writer.<User>builder()
                .environmentVariable(USER_PATH)
                .useClass(User.class)
                .build();
        Context context = new Context(projectWriter, userWriter, taskWriter);
        context.startApp();
        context.terminate();
    }
}
