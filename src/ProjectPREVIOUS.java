import java.util.ArrayList;
import java.util.List;

public class ProjectPREVIOUS {
    private String projectName;
    private List<String> tasks;

    public ProjectPREVIOUS(String projectName) {
        this.projectName = projectName;
        this.tasks = new ArrayList<>();
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    @Override
    public String toString() {
        return projectName;
    }
}
