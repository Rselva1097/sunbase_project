package com.customer.customerapplication.repository;

import com.customer.customerapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    Optional<Customer> findByUsernameOrEmail(String username,String email);

}