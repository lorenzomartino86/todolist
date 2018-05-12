package com.lmartino.todolist.boundary;

import com.lmartino.todolist.boundary.model.UserCredential;
import com.lmartino.todolist.service.TasklistService;
import com.lmartino.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class TasklistController {


    @Autowired
    private TasklistService tasklistService;

    @GetMapping(value = "/{userId}/tasklist")
    public ResponseEntity<?> getTasklist(@PathVariable int userId) {
        return ResponseEntity.ok(tasklistService.getTasklist(userId));
    }
}
