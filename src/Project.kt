public class Project (val projectID: String, val projectName: String) {
    val tasks: MutableMap<String, Task> = mutableMapOf()

    fun addTask(task: Task) {
        tasks[task.taskID] = task
    }

    fun removeTask(taskID: String) {
        tasks.remove(taskID)
    }
}