package com.lmartino.todolist.boundary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Error {
    private String errorMessage;

    public enum Message {AUTHENTICATION_ERROR, MISSING_USER}
}
