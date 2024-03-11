package com.DemoProject.cmp.Service;


import com.DemoProject.cmp.DTO.CustomerDto;
import com.DemoProject.cmp.DTO.TodoDto;
import com.DemoProject.cmp.Entity.Customer;

import java.util.List;

public interface ICustomerService {


    public List<TodoDto> getAllTodos();
    public TodoDto getTodoById(Long id);
    public List<TodoDto> getByStatus(Boolean status);

    public  List<CustomerDto>getAllCustomer();






}
