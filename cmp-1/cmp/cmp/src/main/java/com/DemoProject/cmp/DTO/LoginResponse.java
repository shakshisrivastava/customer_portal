package com.DemoProject.cmp.DTO;

import com.DemoProject.cmp.Entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private String token;
    private AuthStatus authStatus;
    private int status;
    private Role authority;
    private String username;
    private String RefreshToken;
    private String TokenExpirationTime;
    private String message;


}
