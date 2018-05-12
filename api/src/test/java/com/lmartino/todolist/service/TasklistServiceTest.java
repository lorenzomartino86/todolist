package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.TaskPresentation;
import com.lmartino.todolist.repository.UserTaskRepository;
import com.lmartino.todolist.repository.model.UserTask;
import com.lmartino.todolist.repository.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TasklistServiceTest {

    @Mock
    private UserTaskRepository userTaskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TasklistService tasklistService;

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

        final TaskPresentation task = tasklistService.createTask(userId, taskDescription, checked);
        assertNotNull(task);

    }




}