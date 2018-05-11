package com.lmartino.todolist.boundary.handler;

import com.lmartino.todolist.boundary.model.Error;
import com.lmartino.todolist.service.exception.AuthenticationError;
import com.lmartino.todolist.service.exception.DuplicatedUser;
import com.lmartino.todolist.service.exception.MissingUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.lmartino.todolist.boundary.model.Error.Message.AUTHENTICATION_ERROR;
import static com.lmartino.todolist.boundary.model.Error.Message.DUPLICATED_USER;
import static com.lmartino.todolist.boundary.model.Error.Message.MISSING_USER;

@ControllerAdvice
@Component
@Slf4j
public class TodolistHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Error handle(AuthenticationError e){
        log.warn(e.getMessage());
        return Error.builder()
                .errorMessage(AUTHENTICATION_ERROR.name())
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handle(MissingUser e){
        log.warn(e.getMessage());
        return Error.builder()
                .errorMessage(MISSING_USER.name())
                .build();
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error handle(DuplicatedUser e){
        log.warn(e.getMessage());
        return Error.builder()
                .errorMessage(DUPLICATED_USER.name())
                .build();
    }
}
