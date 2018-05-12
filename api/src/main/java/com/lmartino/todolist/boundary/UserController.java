package com.lmartino.todolist.boundary;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserCredential credential) {
        return new ResponseEntity(userService.register(credential.getUsername(), credential.getPassword()),
                HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserCredential credential) {
        return ResponseEntity.ok(userService.login(credential.getUsername(), credential.getPassword()));
    }

}
