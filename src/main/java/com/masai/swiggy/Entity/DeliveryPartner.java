package com.masai.swiggy.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartner {
//deliveryPartnerId, name, and phone number.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryPartnerId;
@NotNull(message = "Delhivery partner name should not null")
    private String name;
@Size(message = "Invalid Mobile number",min = 10,max = 13)
    private String phone;

@OneToMany(mappedBy = "deliveryPartner")
private List<Order> orderList = new ArrayList<>();

}
