class ProjectGraph {
    val projects: MutableMap<String, Project> = mutableMapOf()

    fun addProject(project: Project) {
        projects[project.projectID] = project
    }

    fun removeProject(projectID: String) {
        projects.remove(projectID)
    }

    fun getProjects(): Map<String, Project> {
        return projects.toMap()
    }
}