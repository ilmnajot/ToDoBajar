package uz.ilmnajot.todo.service;

import uz.ilmnajot.todo.model.common.ApiResponse;
import uz.ilmnajot.todo.model.request.TaskRequest;

public interface TaskService {
    ApiResponse addTask(TaskRequest request);

    ApiResponse getTask(Long taskId);

    ApiResponse getAllTasks();

    ApiResponse deleteTask(Long taskId);

    ApiResponse updateTask(TaskRequest request, Long taskId);

    ApiResponse completeTask(Long taskId, TaskRequest request);

    ApiResponse getAllCompleted();

    ApiResponse getAllUnCompleted();
}
