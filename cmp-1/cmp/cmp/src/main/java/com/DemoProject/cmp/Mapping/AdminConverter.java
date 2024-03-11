package com.DemoProject.cmp.Mapping;

import com.DemoProject.cmp.DTO.AdminDto;
import com.DemoProject.cmp.DTO.CustomerDto;
import com.DemoProject.cmp.Entity.Admin;
import com.DemoProject.cmp.Entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter {
    public  AdminDto entityToDto(Admin admin) {
        AdminDto dto = new AdminDto();
        dto.setName(admin.getName());
        dto.setUsername(admin.getUsername());
        dto.setMessage("Admin signed up successfully");
        return dto;
    }
}

