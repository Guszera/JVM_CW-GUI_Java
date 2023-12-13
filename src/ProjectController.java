import java.util.ArrayList;
import java.util.List;
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

    public void addSuccessor(String projectId, String taskId, String successorId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.addSuccessor(taskId, successorId);
        }
    }

    public String getAdjacencyMatrix(String projectId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project == null) {
            return "Project not found.";
        }

        List<Task> tasks = new ArrayList<>(project.getTasks().values());
        int n = tasks.size();

        // Initialize the adjacency matrix
        int[][] adjacencyMatrix = new int[n][n];

        // Populate the adjacency matrix based on task successors
        for (int i = 0; i < n; i++) {
            Task task = tasks.get(i);
            List<String> successors = task.getSuccessors();

            for (String successorId : successors) {
                int successorIndex = tasks.indexOf(project.getTasks().get(successorId));
                adjacencyMatrix[i][successorIndex] = 1;
            }
        }

        // Convert the adjacency matrix to a string for display
        StringBuilder matrixString = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixString.append(adjacencyMatrix[i][j]).append(" ");
            }
            matrixString.append("\n");
        }

        return matrixString.toString();
    }

    public Map<String, Project> getAllProjects() {
        return projectGraph.getProjects();
    }

}
