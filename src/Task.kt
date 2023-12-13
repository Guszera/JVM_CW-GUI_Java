data class Task(val taskId: String, var taskName: String, var duration: Int) {
    val successors: MutableList<String> = mutableListOf()
}

