package com.DemoProject.cmp.Service.UserDetails;

import com.DemoProject.cmp.Entity.Customer;
import com.DemoProject.cmp.Repository.AdminRepository;
import com.DemoProject.cmp.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {

        Customer customer = customerRepository.findByUsername(username);
        if (customer != null)
            return customer;
        else
            return adminRepository.findByUsername(username);

    }

}



