package com.DemoProject.cmp.Mapping;

import com.DemoProject.cmp.DTO.CustomerDto;
import com.DemoProject.cmp.Entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public  CustomerDto entityToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setName(customer.getName());
        dto.setUsername(customer.getUsername());
        dto.setMessage("User Signed up  successfully");
        dto.setStatusCode(HttpStatus.CREATED);
        return dto;
    }
}
