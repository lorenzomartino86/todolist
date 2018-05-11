package com.lmartino.todolist.boundary;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserCredential credential) {
        loginService.login(credential);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

}
