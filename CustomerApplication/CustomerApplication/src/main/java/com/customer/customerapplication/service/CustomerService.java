package com.customer.customerapplication.service;

import com.customer.customerapplication.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    Page<Customer> getAllCustomers(Pageable pageable);

    Customer getCustomerById(Long id);

    void deleteCustomer(Long id);
}