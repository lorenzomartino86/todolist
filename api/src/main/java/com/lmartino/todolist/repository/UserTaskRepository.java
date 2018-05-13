package com.lmartino.todolist.repository;

import com.lmartino.todolist.repository.model.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Integer> {

    @Query(" SELECT ut FROM UserTask ut WHERE ut.user.id = :userId" )
    List<UserTask> findByUserId(@Param("userId") long userId);

}
