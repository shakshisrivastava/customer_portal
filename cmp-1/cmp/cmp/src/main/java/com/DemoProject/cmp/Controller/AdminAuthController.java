package com.DemoProject.cmp.Controller;

import com.DemoProject.cmp.DTO.*;
import com.DemoProject.cmp.Service.AdminAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/admin")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return adminAuthService.login(loginRequest);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AdminDto> signUp(@Valid @RequestBody RegistrationRequest signUpRequest) {
        System.err.println(signUpRequest.toString());
        return ResponseEntity.ok(adminAuthService.signUp(signUpRequest));
    }
}




