data class Task(val taskId: String, var taskName: String, var duration: Int) {
    var successors: MutableList<Task> = mutableListOf()

    // Method to add a successor to the task
    fun addSuccessor(successor: Task) {
        successors.add(successor)
    }

    // Method to remove a successor from the task
    fun removeSuccessor(successor: Task) {
        successors.remove(successor)
    }
}