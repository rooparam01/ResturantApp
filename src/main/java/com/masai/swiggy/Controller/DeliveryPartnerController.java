package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Service.DeliveryPartnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryPartnerController {
    @Autowired
    DeliveryPartnerService deliveryPartnerService;
@PostMapping("/deliverypartners")
public ResponseEntity<DeliveryPartner> addDeliveryPartner(@Valid@RequestBody DeliveryPartner deliveryPartner){
    DeliveryPartner savedDP = deliveryPartnerService.addDeliveyPartner(deliveryPartner);
    return  new ResponseEntity<>(savedDP, HttpStatus.CREATED);
}

}
