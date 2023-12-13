public class Main {
    public static void main(String[] args) {
        ProjectGraph projectGraph = new ProjectGraph();
        ProjectController controller = new ProjectController(projectGraph);
        ProjectManagerGUI gui = new ProjectManagerGUI(controller);
    }
}
