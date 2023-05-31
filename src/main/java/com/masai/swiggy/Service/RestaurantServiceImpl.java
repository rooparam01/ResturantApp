package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.RestaurantRepository;
import com.masai.swiggy.Entity.Restaurant;
import com.masai.swiggy.Exception.SwiggyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService{
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        if(restaurant.getRestaurantId()!=null) throw new SwiggyException("No need to Pass Id");
       Restaurant savedResturant = restaurantRepository.save(restaurant);
       return savedResturant;
    }
}
