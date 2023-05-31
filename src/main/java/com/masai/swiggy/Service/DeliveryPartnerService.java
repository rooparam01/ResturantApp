package com.masai.swiggy.Service;

import com.masai.swiggy.Entity.DeliveryPartner;
import org.springframework.stereotype.Service;

@Service
public interface DeliveryPartnerService {
    public DeliveryPartner addDeliveyPartner(DeliveryPartner deliveryPartner);
}
