package com.DemoProject.cmp.DTO;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  LoginRequest {

    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$")
    private String username;
    @NotBlank(message = "Password Field Cannot be Blank")
    @Size(min = 8, max = 16, message = "Password should be between 8 to 16 characters")
    private String password;


}