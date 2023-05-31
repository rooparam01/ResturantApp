package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.DelhiveryPartnerRepository;
import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Exception.SwiggyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    @Autowired
    DelhiveryPartnerRepository deliveryPartnerRepository;

    @Override
    public DeliveryPartner addDeliveyPartner(DeliveryPartner deliveryPartner) {
        if(deliveryPartner.getDeliveryPartnerId()!=null) throw new SwiggyException("You Not need to Pass id");
        DeliveryPartner savedDP = deliveryPartnerRepository.save(deliveryPartner);
        return savedDP;
    }
}
