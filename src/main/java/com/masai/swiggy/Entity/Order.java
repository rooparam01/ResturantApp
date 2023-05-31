package com.masai.swiggy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
    //orderId, customerId, restaurantId, deliveryPartnerId (nullable), items (a list of menu items), and orderStatus.
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private OrderStatus orderStatus;

    private Double billAmount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Orders_menuItem")
    private List<MenuItem> menuItemList = new ArrayList<>();
@JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private DeliveryPartner deliveryPartner;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Restaurant restaurant;

}
