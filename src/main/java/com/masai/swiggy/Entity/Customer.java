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
public class Customer {
    //customerId, name, email, and address.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NotNull(message = "Name should not be null")
    private String name;
    @Email(message = "Email should be in Correct Formate")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password needed for signUp")
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Address address;
@OneToMany(mappedBy = "customer")
    private List<Order> orderList = new ArrayList<>();

private String role;
}
