package com.masai.swiggy.Service;

import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Entity.Restaurant;

public interface RestaurantService {
    public Restaurant addRestaurant(Restaurant restaurant);
    public Restaurant getUserDetailsByEmail(String email);
}
