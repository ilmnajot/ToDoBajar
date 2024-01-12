package uz.ilmnajot.todo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.todo.model.common.ApiResponse;
import uz.ilmnajot.todo.model.request.TaskRequest;
import uz.ilmnajot.todo.service.TaskService;

import static uz.ilmnajot.todo.utils.Constants.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(ADD_TASK)
    public HttpEntity<ApiResponse> addTask(@RequestBody TaskRequest request) {
        ApiResponse apiResponse = taskService.addTask(request);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(GET_TASK)
    public HttpEntity<ApiResponse> getTask(@PathVariable(name = "taskId") Long taskId) {
        ApiResponse apiResponse = taskService.getTask(taskId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(GET_ALL_TASK)
    public HttpEntity<ApiResponse> getAllTasks() {
        ApiResponse apiResponse = taskService.getAllTasks();
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(DELETE_TASK)
    public HttpEntity<ApiResponse> deleteTask(@PathVariable(name = "taskId") Long taskId) {
        ApiResponse apiResponse = taskService.deleteTask(taskId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PutMapping(UPDATE_TASK)
    public HttpEntity<ApiResponse> updateTask(
            @PathVariable(name = "taskId") Long taskId,
            @RequestBody TaskRequest request) {
        ApiResponse apiResponse = taskService.updateTask(request, taskId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(COMPLETE_TASK)
    public HttpEntity<ApiResponse> completeTask(
            @PathVariable(name = "taskId") Long taskId,
            @RequestBody TaskRequest request) {
        ApiResponse apiResponse = taskService.completeTask(taskId, request);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping(GET_ALL_COMPLETED_TASK)
    public HttpEntity<ApiResponse> getAllCompleted(){
        ApiResponse apiResponse = taskService.getAllCompleted();
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping(GET_ALL_UNCOMPLETED_TASK)
    public HttpEntity<ApiResponse> getAllUnCompleted(){
        ApiResponse apiResponse = taskService.getAllUnCompleted();
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
