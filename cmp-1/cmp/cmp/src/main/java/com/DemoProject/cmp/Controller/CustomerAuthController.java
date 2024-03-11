package com.DemoProject.cmp.Controller;

import com.DemoProject.cmp.DTO.*;
import com.DemoProject.cmp.Service.CustomerAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/customer")
public class CustomerAuthController {

    private final CustomerAuthService customerAuthService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return customerAuthService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomerDto> signUp(@Valid @RequestBody RegistrationRequest signUpRequest) {
        System.err.println(signUpRequest.toString());
        return ResponseEntity.ok(customerAuthService.signUp(signUpRequest));
    }
}