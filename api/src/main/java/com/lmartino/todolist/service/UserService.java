package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.UserPresentation;
import com.lmartino.todolist.repository.UserRepository;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.service.exception.AuthenticationError;
import com.lmartino.todolist.service.exception.DuplicatedUser;
import com.lmartino.todolist.service.exception.MissingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EncodingService encodingService;

    public UserPresentation register(final String username, final String password) {
        final String usernameHash = encodingService.hash(username);
        final String passwordHash = encodingService.hash(password);

        assertNotDuplicatedUser(usernameHash);

        User userToRegister = User.builder()
                .username(usernameHash)
                .password(passwordHash)
                .build();

        final User newUser = repository.save(userToRegister);
        return UserPresentation.builder().id(newUser.getId()).username(username).build();

    }

    public UserPresentation login(final String username, final String password) {
        final String usernameHash = encodingService.hash(username);
        final String passwordHash = encodingService.hash(password);

        final User user = tryToGetUser(usernameHash);
        if (!user.getPassword().equals(passwordHash)){
            throw new AuthenticationError("Password mismatch");
        }
        return UserPresentation.builder().id(user.getId()).username(username).build();
    }

    public User findById(final long userId){
        final Optional<User> user = repository.findById(userId);
        if (!user.isPresent()){
            throw new MissingUser("Missing username");
        }
        return user.get();
    }

    private void assertNotDuplicatedUser(String username) {
        final User user = repository.findByUsername(username);
        if (user != null){
            throw new DuplicatedUser(format("User %s is already stored in database", username));
        }
    }


    private User tryToGetUser(final String usernameHash) {
        final User user = repository.findByUsername(usernameHash);
        if (user == null){
            throw new MissingUser("Missing username");
        }
        return user;
    }

}
