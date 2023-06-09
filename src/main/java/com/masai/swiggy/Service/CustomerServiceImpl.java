package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.CustomerRepository;
import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Exception.SwiggyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer addNewCustomer(Customer customer) {

        customer.setRole("ROLE_CUSTOMER");
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }

    @Override
    public Customer getUserDetailsByEmail(String email) {
        Optional<Customer> opt = customerRepository.findByEmail(email);
        if(opt.isPresent()){
            return opt.get();
        }else{
            throw new SwiggyException("Invalid User Id");
        }
    }
}
