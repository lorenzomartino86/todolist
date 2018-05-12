package com.lmartino.todolist.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lmartino.todolist.boundary.handler.TodolistHandler;
import com.lmartino.todolist.boundary.model.TaskPresentation;
import com.lmartino.todolist.boundary.model.TasklistPresentation;
import com.lmartino.todolist.service.TasklistService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TasklistControllerTest {
    private MockMvc mvc;

    private ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    @Mock
    private TasklistService tasklistService;

    @InjectMocks
    private TasklistController tasklistController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(tasklistController)
                .setControllerAdvice(new TodolistHandler())
                .build();
    }

    @Test
    public void givenUser_whenUserGetTaskList_thenApiReturnUpdatedTaskList() throws Exception {
        final int userId = 1;

        final LocalDateTime now = LocalDateTime.now();

        final TaskPresentation task = TaskPresentation.builder()
                .description("Test Task")
                .lastUpdate(now)
                .checked(false)
                .build();

        final TasklistPresentation tasklistPresentation = TasklistPresentation.builder()
                .taskList(asList(task))
                .build();

        given(tasklistService.getTasklist(userId)).willReturn(tasklistPresentation);


        mvc.perform(get(String.format("/users/%d/tasklist", userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskList.length()", is(1)))
                .andExpect(jsonPath("$.taskList[0].description", is(task.getDescription())))
                .andExpect(jsonPath("$.taskList[0].checked", is(task.isChecked())))
                .andExpect(jsonPath("$.taskList[0].lastUpdate", is(notNullValue())));
    }


    @Test
    public void givenUser_whenUserGetMultipleTasksInList_thenApiReturnUpdatedTaskList() throws Exception {
        final int userId = 1;

        final LocalDateTime now = LocalDateTime.now();

        final TaskPresentation task1 = TaskPresentation.builder()
                .description("Test Task")
                .lastUpdate(now)
                .checked(false)
                .build();
        final TaskPresentation task2 = TaskPresentation.builder()
                .description("Test Task 2")
                .lastUpdate(now.minusDays(2))
                .checked(true)
                .build();

        final TasklistPresentation tasklistPresentation = TasklistPresentation.builder()
                .taskList(asList(task1,task2))
                .build();

        given(tasklistService.getTasklist(userId)).willReturn(tasklistPresentation);


        mvc.perform(get(String.format("/users/%d/tasklist", userId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskList.length()", is(2)))
                .andExpect(jsonPath("$.taskList[0].description", is(task1.getDescription())))
                .andExpect(jsonPath("$.taskList[0].checked", is(task1.isChecked())))
                .andExpect(jsonPath("$.taskList[0].lastUpdate", is(notNullValue())))
                .andExpect(jsonPath("$.taskList[1].description", is(task2.getDescription())))
                .andExpect(jsonPath("$.taskList[1].checked", is(task2.isChecked())))
                .andExpect(jsonPath("$.taskList[1].lastUpdate", is(notNullValue())));
    }


}