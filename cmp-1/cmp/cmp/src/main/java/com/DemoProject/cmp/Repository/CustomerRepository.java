package com.DemoProject.cmp.Repository;

import com.DemoProject.cmp.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
    boolean existsByUsername(String username);


}
