
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ProjectManagerGUI {
    private ProjectController controller;
    private JFrame frame;
    private JTextArea projectTextArea;
    private JTextField projectNameField;
    private JTextField taskIdField;
    private JTextField taskNameField;
    private JTextField taskDurationField;


    // Constructor to initialize the GUI with a ProjectController
    public ProjectManagerGUI(ProjectController controller) {
        this.controller = controller;
        initialize();
    }

    // Method to initialize the GUI components
    private void initialize() {
        frame = new JFrame("Project Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        projectTextArea = new JTextArea();
        projectTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(projectTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        // Text fields for input
        projectNameField = new JTextField();
        inputPanel.add(new JLabel("Project Name:"));
        inputPanel.add(projectNameField);

        taskIdField = new JTextField();
        inputPanel.add(new JLabel("Task ID:"));
        inputPanel.add(taskIdField);

        taskNameField = new JTextField();
        inputPanel.add(new JLabel("Task Name:"));
        inputPanel.add(taskNameField);

        taskDurationField = new JTextField();
        inputPanel.add(new JLabel("Task Duration:"));
        inputPanel.add(taskDurationField);

        // Buttons for various actions
        JButton createProjectButton = new JButton("Create Project");
        JButton editProjectButton = new JButton("Edit Project");
        JButton deleteProjectButton = new JButton("Delete Project");
        JButton addTaskButton = new JButton("Add Task");
        JButton editTaskButton = new JButton("Edit Task");
        JButton deleteTaskButton = new JButton("Delete Task");
        JButton addSuccessorButton = new JButton("Add Successor");
        JButton removeSuccessorButton = new JButton("Remove Successor");
        JButton showAdjacencyMatrixButton = new JButton("Show Adjacency Matrix");

        // Action listeners for buttons
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });

        editProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editProject();
            }
        });

        deleteProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProject();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        editTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTask();
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        addSuccessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSuccessor();
            }
        });

        removeSuccessorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSuccessor();
            }
        });

        showAdjacencyMatrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAdjacencyMatrix();
            }
        });

        // Adding components to inputPanel
        inputPanel.add(createProjectButton);
        inputPanel.add(editProjectButton);
        inputPanel.add(deleteProjectButton);
        inputPanel.add(addTaskButton);
        inputPanel.add(editTaskButton);
        inputPanel.add(deleteTaskButton);
        inputPanel.add(addSuccessorButton);
        inputPanel.add(removeSuccessorButton);
        inputPanel.add(showAdjacencyMatrixButton);

        // Adding inputPanel to mainPanel
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Adding mainPanel to frame
        frame.getContentPane().add(mainPanel);
    }

    // Method to handle creating a new project
    private void createProject() {
        String projectName = projectNameField.getText();
        controller.createProject(projectName);
        updateProjectList();
    }

    // Method to handle editing a project
    private void editProject() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String newProjectName = JOptionPane.showInputDialog("Enter New Project Name:");

        controller.editProject(projectId, newProjectName);
        updateProjectList();
    }

    // Method to handle deleting a project
    private void deleteProject() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        controller.deleteProject(projectId);
        updateProjectList();
    }

    // Method to handle creating a new task
    private void addTask() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = taskIdField.getText();
        String taskName = taskNameField.getText();
        int taskDuration = Integer.parseInt(taskDurationField.getText());

        controller.addTaskToProject(projectId, taskId, taskName, taskDuration);
        updateProjectList();
    }

    // Method to handle editing a task
    private void editTask() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = JOptionPane.showInputDialog("Enter Task ID:");
        String newTaskName = JOptionPane.showInputDialog("Enter New Task Name:");
        int newDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter New Task Duration:"));

        controller.editTask(projectId, taskId, newTaskName, newDuration);
        updateProjectList();
    }

    // Method to handle deleting a task
    private void deleteTask() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = JOptionPane.showInputDialog("Enter Task ID:");
        controller.deleteTask(projectId, taskId);
        updateProjectList();
    }

    // Method to update the Project List with tasks and their successors
    private void updateProjectList() {
        StringBuilder projectList = new StringBuilder();
        Map<String, Project> projects = controller.getAllProjects();

        for (Project project : projects.values()) {
            projectList.append("Project ID: ").append(project.getProjectId()).append(", Project Name: ")
                    .append(project.getProjectName()).append("\n");

            for (Task task : project.getTasks().values()) {
                projectList.append("\tTask ID: ").append(task.getTaskId()).append(", Task Name: ")
                        .append(task.getTaskName()).append(", Duration: ").append(task.getDuration()).append("\n");

                if (!task.getSuccessors().isEmpty()) {
                    projectList.append("\t\tSuccessors: ").append(task.getSuccessors()).append("\n");
                }
            }
        }
        projectTextArea.setText(projectList.toString());
    }

    // Method to handle adding a new successor between two tasks
    private void addSuccessor() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = JOptionPane.showInputDialog("Enter Task ID:");
        String successorId = JOptionPane.showInputDialog("Enter Successor Task ID:");

        controller.addSuccessor(projectId, taskId, successorId);
        updateProjectList();
    }

    // Method to handle removing a successor of a task
    private void removeSuccessor() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = JOptionPane.showInputDialog("Enter Task ID:");
        String successorId = JOptionPane.showInputDialog("Enter Successor Task ID:");

        controller.removeSuccessor(projectId, taskId, successorId);
        updateProjectList();
    }

    // Method to handle to show the Adjacency Matrix
    private void showAdjacencyMatrix() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String adjacencyMatrix = controller.getAdjacencyMatrix(projectId);
        JOptionPane.showMessageDialog(frame, adjacencyMatrix, "Adjacency Matrix", JOptionPane.PLAIN_MESSAGE);
    }

    // Method to display the GUI
    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    // Main method to display the GUI
    public static void main(String[] args) {
        ProjectGraph projectGraph = new ProjectGraph();
        ProjectController controller = new ProjectController(projectGraph);
        ProjectManagerGUI gui = new ProjectManagerGUI(controller);

        gui.display();
    }
}
