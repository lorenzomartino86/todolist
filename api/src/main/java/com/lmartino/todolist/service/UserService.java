package com.lmartino.todolist.service;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.repository.UserRepository;
import com.lmartino.todolist.repository.model.User;
import com.lmartino.todolist.service.exception.AuthenticationError;
import com.lmartino.todolist.service.exception.DuplicatedUser;
import com.lmartino.todolist.service.exception.MissingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void register(UserCredential credential) {
        assertNotDuplicatedUser(credential);

        User userToRegister = User.builder()
                .username(credential.getUsername())
                .password(credential.getPassword())
                .build();

        repository.save(userToRegister);
    }

    private void assertNotDuplicatedUser(UserCredential credential) {
        final User user = repository.findByUsername(credential.getUsername());
        if (user != null){
            throw new DuplicatedUser(format("User %s is already stored in database", user.getUsername()));
        }
    }

    public void login(UserCredential credential) {
        final User user = tryToGetUser(credential);
        if (!user.getPassword().equals(credential.getPassword())){
            throw new AuthenticationError("Password mismatch");
        }
    }

    private User tryToGetUser(UserCredential credential) {
        final User user = repository.findByUsername(credential.getUsername());
        if (user == null){
            throw new MissingUser(format("Missing username %s", credential.getUsername()));
        }
        return user;
    }

}
