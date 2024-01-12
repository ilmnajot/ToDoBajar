package uz.ilmnajot.todo.model.request;

import lombok.Data;

@Data
public class TaskRequest {

    private String name;

    private boolean completed;
}
