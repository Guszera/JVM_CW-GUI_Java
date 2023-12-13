public class Main {
    public static void main(String[] args) {
        // Create an instance of ProjectGraph
        ProjectGraph projectGraph = new ProjectGraph();

        // Create an instance of ProjectController using the ProjectGraph
        ProjectController controller = new ProjectController(projectGraph);

        // Create an instance of ProjectManagerGUI using the ProjectController
        ProjectManagerGUI gui = new ProjectManagerGUI(controller);

        // Display the GUI
        gui.display();
    }
}
