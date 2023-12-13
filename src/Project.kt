class Project private constructor(val projectId: String, var projectName: String) {
    val tasks: MutableMap<String, Task> = mutableMapOf()

    // Method to add a task to the project
    fun addTask(task: Task) {
        tasks[task.taskId] = task
    }

    // Method to remove a task from the project based on its ID
    fun removeTask(taskId: String) {
        tasks.remove(taskId)
    }

    // Companion object to provide a factory method for creating projects
    companion object {
        private var projectIdCounter = 1

        // JvmStatic annotation makes this method accessible from Java code
        @JvmStatic
        fun createProject(projectName: String): Project {
            val projectId = "P${projectIdCounter++}"
            return Project(projectId, projectName)

        }
    }
}
