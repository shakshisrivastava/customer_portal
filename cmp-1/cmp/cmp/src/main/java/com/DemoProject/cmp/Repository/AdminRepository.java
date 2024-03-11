package com.DemoProject.cmp.Repository;

import com.DemoProject.cmp.DTO.AdminDto;
import com.DemoProject.cmp.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
    boolean existsByUsername(String username);

}