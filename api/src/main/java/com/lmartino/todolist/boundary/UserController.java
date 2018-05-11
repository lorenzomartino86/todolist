package com.lmartino.todolist.boundary;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody UserCredential credential) {
        userService.register(credential);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserCredential credential) {
        userService.login(credential);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

}
