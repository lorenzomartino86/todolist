package com.lmartino.todolist.service.exception;

import lombok.Data;

@Data
public class DuplicatedUser extends RuntimeException {

    public DuplicatedUser(String message){
        super(message);
    }
}
