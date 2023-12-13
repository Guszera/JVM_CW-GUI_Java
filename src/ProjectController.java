import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Controller class responsible for handling project-related operations
public class ProjectController {
    private ProjectGraph projectGraph;

    // Constructor to initialize the controller with a ProjectGraph
    public ProjectController(ProjectGraph projectGraph) {
        this.projectGraph = projectGraph;
    }

    // Method to create a new project
    public void createProject(String projectName) {
        Project project = Project.createProject(projectName);
        projectGraph.addProject(project);
    }
    // Method to edit an existing project
    public void editProject(String projectId, String newProjectName) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.setProjectName(newProjectName);
        }
    }
    // Method to delete an existing project
    public void deleteProject(String projectId) {
        projectGraph.removeProject(projectId);
    }

    // Method to add a task to an existing project
    public void addTaskToProject(String projectId, String taskId, String taskName, int duration) {
        Task task = new Task(taskId, taskName, duration);
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.addTask(task);
        }
    }

    // Method to edit the details of an existing task within a project
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
    // Method to delete an existing task within a project
    public void deleteTask(String projectId, String taskId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            project.removeTask(taskId);
        }
    }
    // Method to add a successor task to an existing task within a project
    public void addSuccessor(String projectId, String taskId, String successorId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            Task task = project.getTasks().get(taskId);
            Task successorTask = project.getTasks().get(successorId);

            if (task != null && successorTask != null) {
                task.addSuccessor(successorTask);
            }
        }
    }
    // Method to remove a successor task from an existing task within a project
    public void removeSuccessor(String projectId, String taskId, String successorId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project != null) {
            Task task = project.getTasks().get(taskId);
            Task successorTask = project.getTasks().get(successorId);

            if (task != null && successorTask != null) {
                task.removeSuccessor(successorTask);
            }
        }
    }
    // Method to get the adjacency matrix for the successors of tasks in a project
    public String getAdjacencyMatrix(String projectId) {
        Project project = projectGraph.getProjects().get(projectId);
        if (project == null) {
            return "Project not found.";
        }

        List<Task> tasks = new ArrayList<>(project.getTasks().values());
        int n = tasks.size();

        // Initialize the adjacency matrix
        int[][] adjacencyMatrix = new int[n][n];

        // Convert task IDs to an array for row and column labels
        String[] taskIds = tasks.stream().map(Task::getTaskId).toArray(String[]::new);

        // Populate the adjacency matrix based on task successors
        for (int i = 0; i < n; i++) {
            Task task = tasks.get(i);

            for (Task successorTask : task.getSuccessors()) {
                int successorIndex = tasks.indexOf(successorTask);
                adjacencyMatrix[i][successorIndex] = 1;
            }
        }

        // Convert the adjacency matrix to an HTML table with task IDs as labels
        StringBuilder matrixHtml = new StringBuilder();
        matrixHtml.append("<html><body><table border='1'>");

        // Add column labels as task IDs
        matrixHtml.append("<tr><td></td>");
        for (String taskId : taskIds) {
            matrixHtml.append("<td>").append(taskId).append("</td>");
        }
        matrixHtml.append("</tr>");

        // Add row labels as task IDs
        for (int i = 0; i < n; i++) {
            matrixHtml.append("<tr><td>").append(taskIds[i]).append("</td>");

            for (int j = 0; j < n; j++) {
                matrixHtml.append("<td>").append(adjacencyMatrix[i][j]).append("</td>");
            }

            matrixHtml.append("</tr>");
        }

        matrixHtml.append("</table></body></html>");

        return matrixHtml.toString();
    }
    // Method to retrieve all projects from the ProjectGraph
    public Map<String, Project> getAllProjects() {
        return projectGraph.getProjects();
    }

}