import java.util.Map;

public class ProjectController {
    private ProjectGraph projectGraph;

    public ProjectController(ProjectGraph projectGraph) {
        this.projectGraph = projectGraph;
    }

    public void createProject(String projectName) {
        Project project = Project.createProject(projectName);
        projectGraph.addProject(project);
    }

    public void deleteProject(String projectId) {
        projectGraph.removeProject(projectId);
    }

    public void addTaskToProject(String projectId, String taskId, String taskName, int duration) {
        Task task = new Task(taskId, taskName, duration);
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.addTask(task);
        }
    }

    public void deleteTaskFromProject(String projectId, String taskId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.removeTask(taskId);
        }
    }

    public Project getProjectById(String projectId) {
        return projectGraph.getProjects().get(projectId);
    }

    public Map<String, Project> getAllProjects() {
        return projectGraph.getProjects();
    }

    public Task getTaskById(String projectId, String taskId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            return project.getTasks().get(taskId);
        }
        return null;
    }

    public Map<String, Task> getAllTasksInProject(String projectId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            return project.getTasks();
        }
        return null;
    }
}
