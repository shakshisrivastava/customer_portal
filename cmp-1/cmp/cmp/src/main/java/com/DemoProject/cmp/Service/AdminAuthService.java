package com.DemoProject.cmp.Service;

import com.DemoProject.cmp.DTO.*;
import com.DemoProject.cmp.Entity.Admin;
import com.DemoProject.cmp.Entity.Role;
import com.DemoProject.cmp.Mapping.AdminConverter;
import com.DemoProject.cmp.Repository.AdminRepository;
import com.DemoProject.cmp.Util.JWTUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Data
@Service
@RequiredArgsConstructor
public class AdminAuthService {


    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final JWTUtils jwtUtils;
    private final AdminConverter adminConverter;


    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        LoginResponse response = new LoginResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            Admin admin = adminRepository.findByUsername(loginRequest.getUsername());

            var jwt = jwtUtils.generateToken(admin.getUsername());
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), admin.getUsername());

            response.setStatus(200);
            response.setToken(jwt);
            response.setAuthority(Role.ADMIN);
            response.setUsername(admin.getUsername());
            response.setRefreshToken(refreshToken);
            response.setTokenExpirationTime("10 min");
            response.setAuthStatus(AuthStatus.LOGIN_SUCCESS);
            response.setMessage("Successfully Signed In . Welcome " + admin.getRoles());
        } catch (Exception e) {

            response.setStatus(500);
            response.setAuthStatus(AuthStatus.LOGIN_FAILED);
            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }

    public AdminDto signUp(RegistrationRequest signUpRequest) {
        AdminDto resp = new AdminDto();
        try {

            if (adminRepository.existsByUsername(signUpRequest.getUsername())) {


                resp.setMessage("Admin with this username already exists.");
                resp.setStatusCode(HttpStatus.CONFLICT);
                return resp;
            }

            Admin admin = new Admin();
            admin.setUsername(signUpRequest.getUsername());
            admin.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            admin.setName(signUpRequest.getName());
            admin.setPhone_no(signUpRequest.getPhone_no());


            if (signUpRequest != null && signUpRequest.getPassword() != null) {


                resp = adminConverter.entityToDto(admin);
                adminRepository.save(admin);

            }
        } catch (Exception e) {

            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Internal Server Error. Please try again.");

            e.printStackTrace();
        }
        return resp;
    }

    public ResponseEntity<AdminDto> addAdmin(RegistrationRequest registrationRequest) {

        Admin admin = new Admin();
        admin.setUsername(registrationRequest.getUsername());
        admin.setName(registrationRequest.getName());
        admin.setPhone_no(registrationRequest.getPhone_no());
        admin.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));


        adminRepository.save(admin);
        AdminDto response = adminConverter.entityToDto(admin);
        response.setMessage("Admin Added Successfully");
        response.setStatusCode(HttpStatus.ACCEPTED);


        return ResponseEntity.ok(response);
    }

}