package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.repository.UserRepository;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.service.exception.AuthenticationError;
import com.lmartino.todolist.service.exception.DuplicatedUser;
import com.lmartino.todolist.service.exception.MissingUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.lmartino.todolist.boundary.model.UserCredential.builder;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void userCanRegister() throws Exception {
        final String username = "test";
        final String password = "pwd123";
        UserCredential credential = builder().username(username).password(password).build();
        userService.register(credential);
    }

    @Test(expected = DuplicatedUser.class)
    public void cannotRegisterDuplicatedUser() throws Exception {
        final String username = "test";
        final String password = "pwd123";
        UserCredential credential = builder().username(username).password(password).build();

        // Stub repository
        User user = User.builder().username(username).password(password).build();
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.register(credential);
    }

    @Test
    public void userCanLogin() throws Exception {
        final String username = "test";
        final String password = "pwd123";
        UserCredential credential = builder().username(username).password(password).build();
        User user = User.builder().username(username).password(password).build();

        // Stub repository
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.login(credential);

    }

    @Test(expected = MissingUser.class)
    public void missingUser() throws Exception {
        final String username = "test";
        final String password = "pwd123";
        UserCredential credential = builder().username(username).password(password).build();
        User user = User.builder().username(username).password(password).build();

        // Stub repository
        when(userRepository.findByUsername(username)).thenReturn(null);

        userService.login(credential);

    }


    @Test(expected = AuthenticationError.class)
    public void passwordMismatch() throws Exception {
        final String username = "test";
        final String password = "pwd123";
        final String mismatchedPassword = "pwd123456";
        UserCredential credential = builder().username(username).password(mismatchedPassword).build();
        User user = User.builder().username(username).password(password).build();

        // Stub repository
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.login(credential);

    }
}