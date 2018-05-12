package com.lmartino.todolist.boundary.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TasklistPresentation implements Serializable {
    private List<TaskPresentation> taskList;
}
