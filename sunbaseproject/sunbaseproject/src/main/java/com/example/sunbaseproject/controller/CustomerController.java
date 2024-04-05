package com.example.sunbaseproject.controller;

import com.example.sunbaseproject.model.Customer;
import com.example.sunbaseproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Customer customerRequest){
        Customer customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> response=customerService.getAllCustomer();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int customerID){

        Customer response=customerService.getCustomer(customerID);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping ("/updateCustomer/{id}/{mail}/{address}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id,@PathVariable("mail") String mail,@PathVariable("address") String address){

        Customer response=customerService.updateCustomer(id,mail,address);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int id){

        String response=customerService.deleteCustomer(id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }



}
