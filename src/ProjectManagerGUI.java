import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManagerGUI {
    private ProjectController controller;
    private JFrame frame;
    private JTextArea projectTextArea;
    private JTextField projectNameField;
    private JTextField taskIdField;
    private JTextField taskNameField;
    private JTextField taskDurationField;

    public ProjectManagerGUI(ProjectController controller) {
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Project Manager");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        projectTextArea = new JTextArea();
        projectTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(projectTextArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

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

        JButton createProjectButton = new JButton("Create Project");
        JButton addTaskButton = new JButton("Add Task");

        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        inputPanel.add(createProjectButton);
        inputPanel.add(addTaskButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);
    }

    private void createProject() {
        String projectName = projectNameField.getText();
        controller.createProject(projectName);
        updateProjectList();
    }

    private void addTask() {
        String projectId = JOptionPane.showInputDialog("Enter Project ID:");
        String taskId = taskIdField.getText();
        String taskName = taskNameField.getText();
        int taskDuration = Integer.parseInt(taskDurationField.getText());

        controller.addTaskToProject(projectId, taskId, taskName, taskDuration);
        updateProjectList();
    }

    private void updateProjectList() {
        StringBuilder projectList = new StringBuilder();
        for (Project project : controller.getProjectGraph().getProjects().values()) {
            projectList.append("Project ID: ").append(project.getProjectId()).append(", Project Name: ")
                    .append(project.getProjectName()).append("\n");

            for (Task task : project.getTasks().values()) {
                projectList.append("\tTask ID: ").append(task.getTaskId()).append(", Task Name: ")
                        .append(task.getTaskName()).append(", Duration: ").append(task.getDuration()).append("\n");
            }
        }
        projectTextArea.setText(projectList.toString());
    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }
}
