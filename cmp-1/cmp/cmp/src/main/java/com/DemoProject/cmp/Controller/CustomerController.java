package com.DemoProject.cmp.Controller;

import com.DemoProject.cmp.Config.JWTAuthenticationFilter;
import com.DemoProject.cmp.DTO.TodoDto;
import com.DemoProject.cmp.Exception.TodoException;
import com.DemoProject.cmp.Service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

@RequestMapping("/customer")
public class CustomerController {


    private final CustomerServiceImpl customerService;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    @GetMapping("/get-all-todos")
    public List<TodoDto> getAllTodos() {

        return customerService.getAllTodos();
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable String id) {
        try {
            Integer i = Integer.parseInt(id);
            System.err.println(i);

        } catch (Exception e) {
            throw new TodoException("Id MisMatch Exception", "Please check the id format its not correct",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        TodoDto todo = customerService.getTodoById(Long.parseLong(id));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    @GetMapping("/status/{completed}")
    public ResponseEntity<List<TodoDto>> getByStatus(@PathVariable String completed) {
        if (!isValidBoolean(completed)) {
            throw new TodoException("Invalid Boolean Value", "Please provide a valid boolean value (true/false)",
                    HttpStatus.BAD_REQUEST);
        }

        boolean isCompleted = Boolean.parseBoolean(completed);
        List<TodoDto> todos = customerService.getByStatus(isCompleted);
        return new ResponseEntity<>(todos, HttpStatus.OK);
}
    private boolean isValidBoolean(String value) {
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

}
