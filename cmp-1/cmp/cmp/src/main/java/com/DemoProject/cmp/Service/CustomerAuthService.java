package com.DemoProject.cmp.Service;

import com.DemoProject.cmp.DTO.*;
import com.DemoProject.cmp.Entity.Admin;
import com.DemoProject.cmp.Entity.Customer;
import com.DemoProject.cmp.Entity.Role;
import com.DemoProject.cmp.Exception.CustomerNotFoundException;
import com.DemoProject.cmp.Mapping.AdminConverter;
import com.DemoProject.cmp.Mapping.CustomerConverter;
import com.DemoProject.cmp.Repository.AdminRepository;
import com.DemoProject.cmp.Repository.CustomerRepository;
import com.DemoProject.cmp.Util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class CustomerAuthService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final JWTUtils jwtUtils;
    private final CustomerConverter customerConverter;


    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        LoginResponse response = new LoginResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

           Customer customer = customerRepository.findByUsername(loginRequest.getUsername());

            var jwt = jwtUtils.generateToken(customer.getUsername());
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), customer.getUsername());

            response.setStatus(200);
            response.setToken(jwt);
            response.setAuthority(Role.USER);
            response.setUsername(customer.getUsername());
            response.setRefreshToken(refreshToken);
            response.setTokenExpirationTime("24Hr");
            response.setAuthStatus(AuthStatus.LOGIN_SUCCESS);
            response.setMessage("Successfully Signed In . Welcome " + customer.getRoles());
        } catch (Exception e) {

            throw new CustomerNotFoundException("customer doesn't exist","please registered to login",HttpStatus.NOT_FOUND );
//            response.setStatus(500);
//            response.setAuthStatus(AuthStatus.LOGIN_FAILED);
//            response.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(response);

    }



    public CustomerDto signUp(RegistrationRequest signUpRequest) {
        CustomerDto resp = new CustomerDto();
        try {

            if (customerRepository.existsByUsername(signUpRequest.getUsername())) {


                resp.setMessage("User with this username already exists.");
                resp.setStatusCode(HttpStatus.CONFLICT);
                return resp;
            }

            Customer customer = new Customer();
            customer.setUsername(signUpRequest.getUsername());
            customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            customer.setName(signUpRequest.getName());
            customer.setPhone_no(signUpRequest.getPhone_no());


            if (signUpRequest != null && signUpRequest.getPassword() != null) {



                resp = customerConverter.entityToDto(customer);
                customerRepository.save(customer);

            }
        } catch (Exception e) {

            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            resp.setMessage("Internal Server Error. Please try again.");

            e.printStackTrace();
        }
        return resp;
    }
}
