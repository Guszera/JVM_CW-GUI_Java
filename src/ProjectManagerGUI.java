import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
//test
public class ProjectManagerGUI extends JFrame {
    private List<Project> projects;
    private DefaultListModel<String> projectListModel;
    private JList<String> projectList;
    private JTextField projectNameField;
    private JButton createProjectButton;
    private JButton editProjectButton;
    private JButton deleteProjectButton;

    public ProjectManagerGUI() {
        projects = new ArrayList<>();
        projectListModel = new DefaultListModel<>();
        projectList = new JList<>(projectListModel);
        projectNameField = new JTextField(15);
        createProjectButton = new JButton("Create Project");
        editProjectButton = new JButton("Edit Project");
        deleteProjectButton = new JButton("Delete Project");

        //Set layout
        setLayout(new BorderLayout());
        JPanel projectPanel = new JPanel();
        projectPanel.add(new JLabel("Projects:"));
        projectPanel.add(new JScrollPane(projectList));
        projectPanel.add(projectNameField);
        projectPanel.add(createProjectButton);
        projectPanel.add(editProjectButton);
        projectPanel.add(deleteProjectButton);

        add(projectPanel, BorderLayout.CENTER);

        //add action listeners
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
    }

    private void createProject() {
        String projectName = projectNameField.getText();
        if (!projectName.isEmpty()) {
            Project project = new Project(projectName);
            projects.add(project);
            projectListModel.addElement(projectName);
            projectNameField.setText("");
        }
    }

    private void editProject(){
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newProjectName = JOptionPane.showInputDialog("Enter new project name:");
            if (newProjectName != null && !newProjectName.isEmpty()) {
                projects.get(selectedIndex).setProjectName(newProjectName);
                projectListModel.setElementAt(newProjectName, selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a project to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProject() {
        int selectedIndex = projectList.getSelectedIndex();
        if (selectedIndex != -1) {
            projects.remove(selectedIndex);
            projectListModel.removeElementAt(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Select a project to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProjectManagerGUI gui = new ProjectManagerGUI();
            gui.setSize(400, 300);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gui.setVisible(true);
        });
    }
}