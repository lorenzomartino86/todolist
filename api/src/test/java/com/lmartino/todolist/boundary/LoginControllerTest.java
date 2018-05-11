package com.lmartino.todolist.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lmartino.todolist.boundary.handler.TodolistHandler;
import com.lmartino.todolist.service.exception.AuthenticationError;
import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.lmartino.todolist.boundary.model.UserCredential.builder;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest {

    private MockMvc mvc;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders
                .standaloneSetup(loginController)
                .setControllerAdvice(new TodolistHandler())
                .build();
    }

    private ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @Test
    public void givenCorrectUsernameAndPassword_whenUserLogin_thenApiReturnOk() throws Exception {
        UserCredential credential = builder()
                .username("test")
                .password("pwd123")
                .build();

        mvc.perform(get("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWriter.writeValueAsString(credential)))
                .andExpect(status().isOk());
    }


    @Test
    public void givenWrongUsernameAndPassword_whenUserLogin_thenApiReturnForbidden() throws Exception {
        UserCredential credential = builder()
                .username("test")
                .password("wrongPsw")
                .build();

        // Stub login service
       doThrow(new AuthenticationError("Cannot authenticate user"))
               .when(loginService).login(credential);

        mvc.perform(get("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonWriter.writeValueAsString(credential)))
                .andExpect(status().isForbidden());
    }
}