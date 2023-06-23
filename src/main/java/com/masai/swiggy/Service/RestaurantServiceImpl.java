package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.RestaurantRepository;
import com.masai.swiggy.Entity.Restaurant;
import com.masai.swiggy.Exception.SwiggyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        if(restaurant.getRestaurantId()!=null) throw new SwiggyException("No need to Pass Id");
        restaurant.setRole("ROLE_RESTAURANT");
       Restaurant savedResturant = restaurantRepository.save(restaurant);
       return savedResturant;
    }

    @Override
    public Restaurant getUserDetailsByEmail(String email) {
        Optional<Restaurant> opt = restaurantRepository.findByEmail(email);
        if(opt.isEmpty()) throw new SwiggyException("This email not registered with us");
        Restaurant restaurant = opt.get();
        return restaurant;
    }
}
