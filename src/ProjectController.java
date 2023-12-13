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

    public void editProject(String projectId, String newProjectName) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.setProjectName(newProjectName);
        }
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

    public void editTask(String projectId, String taskId, String newTaskName, int newDuration) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            Task task = project.getTasks().get(taskId);
            if (task != null) {
                task.setTaskName(newTaskName);
                task.setDuration(newDuration);
            }
        }
    }

    public void deleteTask(String projectId, String taskId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.removeTask(taskId);
        }
    }

    public void deleteTaskFromProject(String projectId, String taskId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.removeTask(taskId);
        }
    }

    public Map<String, Project> getAllProjects() {
        return projectGraph.getProjects();
    }

}
