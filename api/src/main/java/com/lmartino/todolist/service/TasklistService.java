package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.TaskPresentation;
import com.lmartino.todolist.boundary.model.TasklistPresentation;
import com.lmartino.todolist.repository.UserTaskRepository;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.repository.model.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.util.Date;

import static com.lmartino.todolist.repository.model.UserTask.*;

@Service
@Transactional
public class TasklistService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserTaskRepository userTaskRepository;

    public TasklistPresentation getTasklist(int userId) {
        return null;
    }

    public TaskPresentation createTask(long userId, String description, boolean checked) {
        final User user = userService.findById(userId);

        final UserTask userTask = builder()
                .user(user)
                .description(description)
                .checked(checked)
                .lastUpdate(new Date())
                .build();

        final UserTask storedUserTask = userTaskRepository.save(userTask);

        return TaskPresentation.builder()
                .description(storedUserTask.getDescription())
                .checked(storedUserTask.isChecked())
                .lastUpdate(storedUserTask.getLastUpdate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime())
                .build();
    }
}
