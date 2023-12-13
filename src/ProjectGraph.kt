class ProjectGraph {
    private val projects: MutableMap<String, Project> = mutableMapOf()

    // Method to add a project to the project graph
    fun addProject(project: Project) {
        projects[project.projectId] = project
    }

    // Method to remove a project from the project graph based on its ID
    fun removeProject(projectId: String) {
        projects.remove(projectId)
    }

    // Method to get a read-only view of the projects in the project graph
    fun getProjects(): Map<String, Project> {
        return projects.toMap()
    }
}
