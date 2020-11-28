package com.crispyecho.kotlinspring.resource

import com.crispyecho.kotlinspring.model.Task
import com.crispyecho.kotlinspring.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/tasks")
class TaskResource(private val taskService: TaskService) {

  @GetMapping
  fun getTasks(): List<Task> = taskService.getTasks()

  @PostMapping
  fun addTask(@Valid @RequestBody task: Task): ResponseEntity<Task> =
    taskService.addTask(task)

  @GetMapping("/{id}")
  fun getTaskById(@PathVariable(value="id") taskId: Long): ResponseEntity<Task> =
    taskService.getTaskById(taskId)

  @GetMapping("/title/{title}")
  fun getTaskByTitle(@PathVariable(value="title") title: String): ResponseEntity<Task> =
    taskService.getTaskByTitle(title)


  @PutMapping("/{id}")
  fun updateTaskById(
    @PathVariable(value="id") taskId: Long,
    @Valid @RequestBody newTask: Task): ResponseEntity<Task> =
      taskService.putTask(taskId, newTask)

  @DeleteMapping("/{id}")
  fun deleteTask(@PathVariable(value = "id") taskId: Long): ResponseEntity<Void> =
    taskService.deleteTask(taskId)

}