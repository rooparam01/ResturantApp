package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Entity.Restaurant;
import com.masai.swiggy.Service.RestaurantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant){
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        Restaurant savedResturant = restaurantService.addRestaurant(restaurant);
        log.info("Resturant added Successful : Resturant Controller Class");
        return new ResponseEntity<>(savedResturant, HttpStatus.CREATED);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloRestaurant(){

        return  new ResponseEntity<>("Hello from delivery helloRestaurant controller", HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<Restaurant> getLoggedInCustomerDetailsHandler(Authentication auth){

        System.out.println(auth); // this Authentication object having Principle object details

       Restaurant restaurant = restaurantService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(restaurant, HttpStatus.ACCEPTED);
    }
}
