data class Task(val taskId: String, var taskName: String, var duration: Int) {
    var successors: MutableList<Task> = mutableListOf()

    fun addSuccessor(successor: Task) {
        successors.add(successor)
    }

    fun removeSuccessor(successor: Task) {
        successors.remove(successor)
    }
}