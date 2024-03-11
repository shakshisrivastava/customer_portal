package com.DemoProject.cmp.Controller;

import com.DemoProject.cmp.DTO.AdminDto;
import com.DemoProject.cmp.DTO.CustomerDto;
import com.DemoProject.cmp.DTO.RegistrationRequest;
import com.DemoProject.cmp.Exception.CustomerNotFoundException;
import com.DemoProject.cmp.Service.AdminAuthService;
import com.DemoProject.cmp.Service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final CustomerServiceImpl customerService;
    private final AdminAuthService adminService;

     @GetMapping("/get-all-customer")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> custom=customerService.getAllCustomer();

        if(custom!=null && custom.size()>0)
        return custom;

       throw new CustomerNotFoundException("Unable to Get Customers", "No Customer Found", HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addAdmin")
    public ResponseEntity<AdminDto> addAdmin(@Valid @RequestBody RegistrationRequest registrationRequest) {
        return adminService.addAdmin(registrationRequest);

    }

}