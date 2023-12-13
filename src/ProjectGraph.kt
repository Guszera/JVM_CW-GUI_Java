class ProjectGraph {
    private val projects: MutableMap<String, Project> = mutableMapOf()

    fun addProject(project: Project) {
        projects[project.projectId] = project
    }

    fun removeProject(projectId: String) {
        projects.remove(projectId)
    }

    fun getProjects(): Map<String, Project> {
        return projects.toMap()
    }
}
