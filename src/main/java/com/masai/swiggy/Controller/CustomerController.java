package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/customers")
    public ResponseEntity<Customer> addnewCustomer(@Valid @RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
      Customer savedCustomer =  customerService.addNewCustomer(customer);
      return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){

        System.out.println(auth); // this Authentication object having Principle object details

        Customer customer= customerService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(customer.getName()+"Logged In Successfully", HttpStatus.ACCEPTED);
    }


}
