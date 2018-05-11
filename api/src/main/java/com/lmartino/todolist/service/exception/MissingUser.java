package com.lmartino.todolist.service.exception;

import lombok.Data;

@Data
public class MissingUser extends RuntimeException {

    public MissingUser(String message){
        super(message);
    }
}
