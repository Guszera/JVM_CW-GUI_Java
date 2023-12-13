class Project private constructor(val projectId: String, val projectName: String) {
    val tasks: MutableMap<String, Task> = mutableMapOf()

    fun addTask(task: Task) {
        tasks[task.taskId] = task
    }

    fun removeTask(taskId: String) {
        tasks.remove(taskId)
    }

    companion object {
        private var projectIdCounter = 1

        @JvmStatic
        fun createProject(projectName: String): Project {
            val projectId = "P${projectIdCounter++}"
            return Project(projectId, projectName)

        }
    }
}
