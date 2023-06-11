package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.CustomerRepository;
import com.masai.swiggy.Entity.Authority;
import com.masai.swiggy.Entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> opt = customerRepository.findByEmail(username);
        if(opt.isPresent()){
            Customer customer = opt.get();
            List<GrantedAuthority>  authorities = new ArrayList<>();
            List<Authority> auths = customer.getAuthorities();

            for (Authority auth : auths) {
                SimpleGrantedAuthority sga = new SimpleGrantedAuthority(auth.getName());
                authorities.add(sga);
            }

            System.out.println("granted authorities " + authorities);
            return new User(customer.getEmail(),customer.getPassword(),authorities);

        }else{
            throw new BadCredentialsException("User Details not found with this username: "+username);
        }
    }
}
