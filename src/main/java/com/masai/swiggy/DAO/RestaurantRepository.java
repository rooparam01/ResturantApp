package com.masai.swiggy.DAO;

import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    public Optional<Restaurant> findByEmail(String email);
}
