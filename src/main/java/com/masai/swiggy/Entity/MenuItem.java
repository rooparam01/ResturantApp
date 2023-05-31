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
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuitemId;

    @NotNull(message = "Menu item name not should be null")
    private String name;

    @NotNull(message = "Menu item price should notnull")
    private Double price;

    @ManyToMany
    private List<Order> orderList = new ArrayList<>();



}
