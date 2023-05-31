package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/customers")
    public ResponseEntity<Customer> addnewCustomer(@Valid @RequestBody Customer customer){
      Customer savedCustomer =  customerService.addNewCustomer(customer);
      return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }


}
