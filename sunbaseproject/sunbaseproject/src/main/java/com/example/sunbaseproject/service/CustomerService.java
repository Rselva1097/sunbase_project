package com.example.sunbaseproject.service;

import com.example.sunbaseproject.exception.CustomerNotFound;
import com.example.sunbaseproject.model.Customer;
import com.example.sunbaseproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public Customer addCustomer(Customer customerRequest) {

        Customer response=customerRepository.save(customerRequest);

        return response;
    }

    public List<Customer> getAllCustomer() {

        List<Customer> response=customerRepository.findAll();

        if(response.size() == 0){
             throw new CustomerNotFound("Customers Not Exists!");
        }

        return response;
    }

    public Customer getCustomer(int customerID) {
        Optional<Customer> response=customerRepository.findById(customerID);

        if(response.get() == null){
            throw new CustomerNotFound("Customer with given ID Not Exists!");
        }

        return response.get();
    }

    public Customer updateCustomer(int id,String mail, String address) {
        Optional<Customer> response=customerRepository.findById(id);

        if(response.get() == null){
            throw new CustomerNotFound("Customer with given ID Not Exists!");
        }

        Customer beforeUpdate=response.get();

        beforeUpdate.setEmail(mail);
        beforeUpdate.setAddress(address);

        Customer afterUpdate=customerRepository.save(beforeUpdate);

        return afterUpdate;
    }

    public String deleteCustomer(int id) {

        Optional<Customer> response=customerRepository.findById(id);

        if(response.get() == null){
            throw new CustomerNotFound("Customer with given ID Not Exists!");
        }

//        Customer customer=response.get();

        customerRepository.deleteById(id);

        return "Customer deletes successfully";
    }
}
