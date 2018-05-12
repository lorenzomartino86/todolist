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
import java.util.Optional;

import static com.lmartino.todolist.repository.model.UserTask.*;

@Service
@Transactional
public class UserTaskService {
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

        return toPresentation(storedUserTask);
    }

    public TaskPresentation updateTask(long userId, long taskId, String description, boolean checked) {
        userService.findById(userId);
        final Optional<UserTask> userTaskEntity = userTaskRepository.findById(taskId);

        if (!userTaskEntity.isPresent()){
            throw new RuntimeException("Task list not found");
        }

        final UserTask userTask = userTaskEntity.get();
        userTask.setChecked(checked);
        userTask.setDescription(description);
        userTask.setLastUpdate(new Date());

        final UserTask updatedUserTask = userTaskRepository.save(userTask);

        return toPresentation(updatedUserTask);
    }

    public void deleteTask(long userId, long taskId) {
        userService.findById(userId);
        final Optional<UserTask> userTaskEntity = userTaskRepository.findById(taskId);

        if (!userTaskEntity.isPresent()){
            throw new RuntimeException("Task list not found");
        }
        userTaskRepository.deleteById(taskId);
    }

    private TaskPresentation toPresentation(UserTask updatedUserTask) {
        return TaskPresentation.builder()
                .description(updatedUserTask.getDescription())
                .checked(updatedUserTask.isChecked())
                .lastUpdate(updatedUserTask.getLastUpdate().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .build();
    }

}
