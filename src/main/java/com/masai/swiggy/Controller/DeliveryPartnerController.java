package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Service.DeliveryPartnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliverypartners")
public class DeliveryPartnerController {
    @Autowired
    DeliveryPartnerService deliveryPartnerService;

    @Autowired
    PasswordEncoder passwordEncoder;
@PostMapping
public ResponseEntity<DeliveryPartner> addDeliveryPartner(@Valid@RequestBody DeliveryPartner deliveryPartner){
    deliveryPartner.setPassword(passwordEncoder.encode(deliveryPartner.getPassword()));
    DeliveryPartner savedDP = deliveryPartnerService.addDeliveyPartner(deliveryPartner);
    return  new ResponseEntity<>(savedDP, HttpStatus.CREATED);
}

@GetMapping("/hello")
public ResponseEntity<String> helloDeliveryPartner(){

    return  new ResponseEntity<>("Hello from delivery partner controller", HttpStatus.CREATED);
}

    @GetMapping("/signIn")
    public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){

        System.out.println(auth); // this Authentication object having Principle object details

        DeliveryPartner deliveryPartner= deliveryPartnerService.getUserDetailsByEmail(auth.getName());

        return new ResponseEntity<>(deliveryPartner.getName()+" Logged In Successfully", HttpStatus.ACCEPTED);
    }

}
