package com.lmartino.todolist.repository;

import com.lmartino.todolist.repository.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
}
