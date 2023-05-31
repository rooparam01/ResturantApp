package com.masai.swiggy.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    //restaurantId, name, and address.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;
    @NotNull(message = "Name not should be null")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orderList = new ArrayList<>();



}
