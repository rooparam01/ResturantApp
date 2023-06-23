package com.masai.swiggy.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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

    @Column(unique = true)
    @Email(message = "Email should in correct formate")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<MenuItem> menuItemList = new ArrayList<>();

    private String role;

}
