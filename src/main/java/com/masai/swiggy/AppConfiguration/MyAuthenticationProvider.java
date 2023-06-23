package com.masai.swiggy.AppConfiguration;

import com.masai.swiggy.DAO.CustomerRepository;
import com.masai.swiggy.DAO.DelhiveryPartnerRepository;
import com.masai.swiggy.DAO.RestaurantRepository;
import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DelhiveryPartnerRepository delhiveryPartnerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Out Authentication Provider is used...");

        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        if (username.startsWith("CUST_")) {
            // Authenticate customer
            String customerEmail = username.substring(5); // Remove "CUST_" prefix
            Optional<Customer> opt = customerRepository.findByEmail(customerEmail);
            Customer customer= opt.get();

            if (passwordEncoder.matches(pwd, customer.getPassword())) {

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.getRole()));
                if(opt.isEmpty()){
                    throw new BadCredentialsException("No user registered with this email");
                }
                return new UsernamePasswordAuthenticationToken(customerEmail, pwd, authorities);

            } else {
                throw new BadCredentialsException("No user registered with this email");

            }
        } else if (username.startsWith("DP_")) {
            // Authenticate delivery partner
            String partnerEmail = username.substring(3); // Remove "DP_" prefix
            Optional<DeliveryPartner> partnerOpt = delhiveryPartnerRepository.findByEmail(partnerEmail);

            if (partnerOpt.isEmpty()) {
                throw new BadCredentialsException("No user registered with this email");
            } else {
                DeliveryPartner partner = partnerOpt.get();
                if (passwordEncoder.matches(pwd, partner.getPassword())) {
                    List<GrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority(partner.getRole()));
                    return new UsernamePasswordAuthenticationToken(partnerEmail, pwd, authorities);
                } else {
                    throw new BadCredentialsException("Invalid password");
                }
            }
        } else if (username.startsWith("REST_")) {
            // Authenticate restaurant
            String restaurantEmail = username.substring(5); // Remove "REST_" prefix
            Optional<Restaurant> restaurantOpt = restaurantRepository.findByEmail(restaurantEmail);

            if (restaurantOpt.isEmpty()) {
                throw new BadCredentialsException("No user registered with this email");
            } else {
                Restaurant restaurant = restaurantOpt.get();
                if (passwordEncoder.matches(pwd, restaurant.getPassword())) {
                    List<GrantedAuthority> authorities = Collections.singletonList(
                            new SimpleGrantedAuthority(restaurant.getRole()));
                    return new UsernamePasswordAuthenticationToken(restaurantEmail, pwd, authorities);
                } else {
                    throw new BadCredentialsException("Invalid password");
                }
            }
        } else {
            throw new BadCredentialsException("Invalid username prefix");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
