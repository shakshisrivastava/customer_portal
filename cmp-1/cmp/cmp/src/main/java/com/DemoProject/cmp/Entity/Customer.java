package com.DemoProject.cmp.Entity;

import com.DemoProject.cmp.DTO.CustomerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name;
    private String username;
    private String password;
    private String phone_no;
    @Enumerated(EnumType.STRING)
    private Role roles=Role.USER;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleString = roles.toString();
        return List.of(new SimpleGrantedAuthority(roleString));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public CustomerDto toCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(this.name);
        customerDto.setUsername(this.username);
        return customerDto;
    }
}
