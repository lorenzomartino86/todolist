package com.lmartino.todolist.boundary.handler;

import com.lmartino.todolist.boundary.model.Error;
import com.lmartino.todolist.service.exception.AuthenticationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.lmartino.todolist.boundary.model.Error.Message.AUTHENTICATION_ERROR;

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
}
