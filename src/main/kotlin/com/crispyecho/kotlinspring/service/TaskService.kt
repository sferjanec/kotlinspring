package com.crispyecho.kotlinspring.service

import com.crispyecho.kotlinspring.model.Task
import com.crispyecho.kotlinspring.repository.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory



@Service
class TaskService(private val taskRepository: TaskRepository) {

  companion object {
        private val logger = LoggerFactory.getLogger(TaskService::class.java)
    }

  fun getTasks(): List<Task> =
    taskRepository.findAll()

  fun addTask(task: Task): ResponseEntity<Task> =
    ResponseEntity.ok(taskRepository.save(task))

  fun getTaskById(taskId: Long): ResponseEntity<Task> =
    taskRepository.findById(taskId).map {
      task -> ResponseEntity.ok(task)
    }.orElse(ResponseEntity.notFound().build())


  fun getTaskByTitle(title: String): ResponseEntity<Task> {
    val tasks: List<Task> = taskRepository.findByTitle(title)

    return if (tasks.isNullOrEmpty()) ResponseEntity.notFound().build()
      else ResponseEntity.ok(tasks.get(0))

  }

    fun putTask(taskId: Long, newTask: Task): ResponseEntity<Task> =
      taskRepository.findById(taskId).map { currentTask ->
        val updatedTask: Task =
          currentTask.copy(
            title = newTask.title,
            description = newTask.description,
            status = newTask.status,
            startDate = newTask.startDate,
            priority = newTask.priority,
            dueDate = newTask.dueDate
          )
        ResponseEntity.ok().body(taskRepository.save(updatedTask))
      }.orElse(ResponseEntity.notFound().build())

      fun deleteTask(taskId: Long): ResponseEntity<Void> =
        taskRepository.findById(taskId).map { task ->
            taskRepository.delete(task)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
          }.orElse(ResponseEntity.notFound().build())
}