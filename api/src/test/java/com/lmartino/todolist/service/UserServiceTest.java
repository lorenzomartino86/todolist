package com.lmartino.todolist.service;

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.when;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EncodingService encodingService;

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

        // Stub encoding service and repository
        User user = User.builder().id(1L).username(username).password(password).build();
        when(encodingService.hash(username)).thenReturn(username);
        when(encodingService.hash(password)).thenReturn(password);
        when(userRepository.save(any())).thenReturn(user);

        userService.register("test", "pwd123");
    }

    @Test(expected = DuplicatedUser.class)
    public void cannotRegisterDuplicatedUser() throws Exception {
        final String username = "test";
        final String password = "pwd123";

        // Stub encoding service and repository
        User user = User.builder().username(username).password(password).build();
        when(encodingService.hash(username)).thenReturn(username);
        when(encodingService.hash(password)).thenReturn(password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.register(username, password);
    }

    @Test
    public void userCanLogin() throws Exception {
        final String username = "test";
        final String password = "pwd123";

        // Stub encoding service and repository
        User user = User.builder().id(1L).username(username).password(password).build();
        when(encodingService.hash(username)).thenReturn(username);
        when(encodingService.hash(password)).thenReturn(password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.login(username, password);

    }

    @Test(expected = MissingUser.class)
    public void missingUser() throws Exception {
        final String username = "test";
        final String password = "pwd123";

        // Stub encoding service and repository
        when(encodingService.hash(username)).thenReturn(username);
        when(encodingService.hash(password)).thenReturn(password);
        when(userRepository.findByUsername(username)).thenReturn(null);

        userService.login(username, password);

    }


    @Test(expected = AuthenticationError.class)
    public void passwordMismatch() throws Exception {
        final String username = "test";
        final String storedPassword = "pwd123";
        final String inputPassword = "pwd123456";

        // Stub encoding service and repository
        User user = User.builder().username(username).password(storedPassword).build();
        when(encodingService.hash(username)).thenReturn(username);
        when(encodingService.hash(inputPassword)).thenReturn(inputPassword);
        when(userRepository.findByUsername(username)).thenReturn(user);

        userService.login(username, inputPassword);

    }
}