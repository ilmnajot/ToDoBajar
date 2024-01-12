package uz.ilmnajot.todo.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String message;
    private boolean success;
    private Object info;


    public ApiResponse(String message, Object info){
        this.message = message;
        this.info = info;
    }
    public ApiResponse(boolean success, Object info){
        this.success = success;
        this.info = info;
    }

}
