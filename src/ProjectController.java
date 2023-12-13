public class ProjectController {
    private ProjectGraph projectGraph;

    public ProjectController(ProjectGraph projectGraph) {
        this.projectGraph = projectGraph;
    }

    public void createProject(String projectID, String projectName) {
        Project project = new Project(projectID, projectName);
        projectGraph.addProject(project);
    }

    public void deleteProject(String projectID) {
        projectGraph.removeProject(projectID);
    }

    public void addTaskToProject(String projectID, String taskID, String taskName, int duration) {
        Task task = new Task(taskID, taskName, duration);
        Project project = projectGraph.projects.get(projectID);
        if (project != null) {
            project.addTask(task);
        }
    }

    public void deleteTaskFromProject(String projectID, String taskID) {
        Project project = projectGraph.projects.get(projectID);
        if (project != null) {
            project.removeTask(taskID);
        }
    }
}
