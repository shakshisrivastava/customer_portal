package com.DemoProject.cmp.DTO;

import com.DemoProject.cmp.Entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be empty")
    private String name ;
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$")
    private String username;
    @NotBlank(message = "Password Field Cannot be Blank")
    @Size(min = 8, max = 16, message = "Password should be between 8 to 16 characters")
    private String password;
    @Size(min = 10 , max = 10, message = "phone_no must be 10 digit")
    private  String phone_no;


}

