package savelogic;
 import application.ProjectService;
import application.TaskService;
import application.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonWriter {

    private final FileOperator fileOperator = new FileOperator();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final ProjectService projectService;
    private final TaskService taskService;
    private final UserService userService;


    public JsonWriter(ProjectService projectService, TaskService taskService, UserService userService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
    }


    public void writeProject() throws IOException {

        objectMapper.writeValue(fileOperator.getFileProjects(), projectService.getAllProjects());
    }

    public void writeTask() throws IOException {

        objectMapper.writeValue(fileOperator.getFileTasks(), taskService.getTaskList());
    }

    public void writeUser() throws IOException {

        objectMapper.writeValue(fileOperator.getFileUsers(), userService.getUsers());
    }


}
