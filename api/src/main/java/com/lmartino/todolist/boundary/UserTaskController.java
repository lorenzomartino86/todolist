package com.lmartino.todolist.boundary;

import com.lmartino.todolist.boundary.model.TaskPresentation;
import com.lmartino.todolist.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserTaskController {


    @Autowired
    private UserTaskService userTaskService;

    @GetMapping(value = "/{userId}/tasks")
    public ResponseEntity<?> getTasklist(@PathVariable int userId) {
        return ResponseEntity.ok(userTaskService.getTasklist(userId));
    }

    @DeleteMapping(value = "/{userId}/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable int userId, @PathVariable int taskId) {
        userTaskService.deleteTask(userId, taskId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{userId}/tasks")
    public ResponseEntity<?> createTask(@PathVariable int userId, @RequestBody TaskPresentation body) {
        return new ResponseEntity(userTaskService.createTask(userId, body.getDescription(), body.isChecked()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable int userId, @PathVariable int taskId, @RequestBody TaskPresentation body) {
        return ResponseEntity.ok(userTaskService.updateTask(userId, taskId, body.getDescription(), body.isChecked()));
    }
}
