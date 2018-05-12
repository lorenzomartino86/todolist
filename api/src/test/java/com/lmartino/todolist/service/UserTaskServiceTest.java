package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.TaskPresentation;
import com.lmartino.todolist.repository.UserTaskRepository;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.repository.model.UserTask;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UserTaskServiceTest {

    @Mock
    private UserTaskRepository userTaskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserTaskService userTaskService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userCanCreateNewTask() throws Exception {
        final long userId = 1L;
        final String username = "test";
        final String password = "pwd123";
        final User user = User.builder().id(userId).username(username).password(password).build();

        final String taskDescription = "Test Task";
        final Date currentDate = new Date();
        final boolean checked = false;
        final UserTask userTask = UserTask.builder().user(user).checked(checked).lastUpdate(currentDate).description(taskDescription).build();

        when(userTaskRepository.save(any(UserTask.class))).thenReturn(userTask);

        final TaskPresentation task = userTaskService.createTask(userId, taskDescription, checked);
        assertNotNull(task);

    }


    @Test
    public void userCanChangeStatusOfATask() throws Exception {
        final long userId = 1L;
        final String username = "test";
        final String password = "pwd123";
        final User user = User.builder().id(userId).username(username).password(password).build();

        final String taskDescription = "Test Task";
        final Date currentDate = new Date();
        final boolean checked = false;
        final long taskId = 1L;
        final UserTask userTask = UserTask.builder().id(taskId).user(user).checked(checked).lastUpdate(currentDate).description(taskDescription).build();

        when(userTaskRepository.findById(taskId)).thenReturn(Optional.of(userTask));
        when(userTaskRepository.save(any(UserTask.class))).thenReturn(userTask);

        final TaskPresentation task = userTaskService.updateTask(userId, taskId, "NEW DESCRIPTION", true);
        assertNotNull(task);

        assertThat(task.isChecked(), is(true));
        assertThat(task.getDescription(), is("NEW DESCRIPTION"));

    }

    @Test
    public void userCanDeleteATask() throws Exception {
        final long userId = 1L;
        final String username = "test";
        final String password = "pwd123";
        final User user = User.builder().id(userId).username(username).password(password).build();

        final String taskDescription = "Test Task";
        final Date currentDate = new Date();
        final boolean checked = false;
        final long taskId = 1L;
        final UserTask userTask = UserTask.builder().id(taskId).user(user).checked(checked).lastUpdate(currentDate).description(taskDescription).build();

        when(userTaskRepository.findById(taskId)).thenReturn(Optional.of(userTask));
        doNothing().when(userTaskRepository).delete(any(UserTask.class));

        userTaskService.deleteTask(userId, taskId);

    }

}