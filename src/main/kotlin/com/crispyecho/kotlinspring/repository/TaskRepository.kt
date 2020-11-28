package com.crispyecho.kotlinspring.repository

import com.crispyecho.kotlinspring.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@Repository
interface TaskRepository :JpaRepository<Task, Long> {

  @Query("SELECT * from tasks where title = :title", nativeQuery = true)
  fun findByTitle(@Param("title") title: String): List<Task>
}


