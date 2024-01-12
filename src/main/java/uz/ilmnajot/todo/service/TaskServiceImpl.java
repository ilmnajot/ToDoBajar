package uz.ilmnajot.todo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.todo.entity.Task;
import uz.ilmnajot.todo.exception.BaseException;
import uz.ilmnajot.todo.model.common.ApiResponse;
import uz.ilmnajot.todo.model.request.TaskRequest;
import uz.ilmnajot.todo.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ApiResponse addTask(TaskRequest request) {
        Optional<Task> taskByName = taskRepository.findTaskByName(request.getName());
        if (taskByName.isPresent()) {
            throw new BaseException("this task already exists", HttpStatus.BAD_REQUEST);
        }
        Task task = new Task();
        task.setName(request.getName());
        Task savedTask = taskRepository.save(task);
        return new ApiResponse("success", true, savedTask);
    }

    @Override
    public ApiResponse getTask(Long taskId) {
        Task task = getTaskById(taskId);
        return new ApiResponse("success", true, task);
        }

    @Override
    public ApiResponse getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new BaseException("no tasks found", HttpStatus.NOT_FOUND);
        }
//        List<Task> collect = tasks
//                .stream()
//                .map(Task::new)
//                .collect(Collectors.toList());
        return new ApiResponse("success", true, tasks);
    }

    @Override
    public ApiResponse deleteTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()){
            taskRepository.deleteById(taskId);
            return new ApiResponse("success", true, "task deleted successfully");
        }
        throw new BaseException("Task not found with id " + taskId, HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse updateTask(TaskRequest request, Long taskId) {
        Task task = getTaskById(taskId);
            task.setId(taskId);
            task.setCompleted(request.isCompleted());
            task.setName(request.getName());
            Task savedTask = taskRepository.save(task);
            return new ApiResponse("success", true,savedTask);

    }

    @Override
    public ApiResponse completeTask(Long taskId, TaskRequest request) {
        Task task = getTaskById(taskId);
        task.setId(taskId);
        task.setCompleted(true);
        Task savedTask = taskRepository.save(task);
        return new ApiResponse("success", true, savedTask);
    }

    @Override
    public ApiResponse getAllCompleted() {
        Optional<Task> allByCompletedTrue = taskRepository.findAllByCompletedTrue();
        if (allByCompletedTrue.isPresent()) {
//            List<Task> tasks = allByCompletedTrue
//                    .stream()
//                    .map(task -> new Task())
//                    .toList();
            return new ApiResponse("success", true, allByCompletedTrue);
        }
        throw new BaseException("there no completed tasks", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse getAllUnCompleted() {
        Optional<Task> allByCompletedFalse = taskRepository.findAllByCompletedFalse();
        if (allByCompletedFalse.isPresent()) {
    return new ApiResponse("success", true, allByCompletedFalse);
        }
        throw new BaseException("there no Uncompleted tasks", HttpStatus.NOT_FOUND);
    }

    /////////////////////*********GET_TASK_BY_ID************///////////////
    public Task getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        }
        throw new BaseException("there is no task with id " + taskId, HttpStatus.NOT_FOUND);
    }
}
