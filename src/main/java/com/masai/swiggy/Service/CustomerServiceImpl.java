package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.CustomerRepository;
import com.masai.swiggy.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer addNewCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer;
    }
}
