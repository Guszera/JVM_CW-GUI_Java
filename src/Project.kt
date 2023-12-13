class Project private constructor(val projectId: String, var projectName: String) {
    val tasks: MutableMap<String, Task> = mutableMapOf()

    fun addTask(task: Task) {
        tasks[task.taskId] = task
    }

    fun addSuccessor(taskId: String, successorId: String) {
        tasks[taskId]?.successors?.add(successorId)
    }

    fun removeTask(taskId: String) {
        tasks.remove(taskId)
        tasks.values.forEach{ it.successors.remove(taskId) }
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
