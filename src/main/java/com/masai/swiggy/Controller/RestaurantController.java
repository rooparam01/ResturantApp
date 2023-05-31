package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.Restaurant;
import com.masai.swiggy.Service.RestaurantService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @PostMapping("/restuarants")
    public ResponseEntity<Restaurant> addRestaurant(@Valid @RequestBody Restaurant restaurant){
        Restaurant savedResturant = restaurantService.addRestaurant(restaurant);
        log.info("Resturant added Successful : Resturant Controller Class");
        return new ResponseEntity<>(savedResturant, HttpStatus.CREATED);
    }
}
