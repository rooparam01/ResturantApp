package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.DelhiveryPartnerRepository;
import com.masai.swiggy.Entity.DeliveryPartner;
import com.masai.swiggy.Exception.SwiggyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    @Autowired
    DelhiveryPartnerRepository deliveryPartnerRepository;

    @Override
    public DeliveryPartner addDeliveyPartner(DeliveryPartner deliveryPartner) {
        if(deliveryPartner.getDeliveryPartnerId()!=null) throw new SwiggyException("You Not need to Pass id");
        deliveryPartner.setRole("ROLE_DELIVERYPARTNER");
        DeliveryPartner savedDP = deliveryPartnerRepository.save(deliveryPartner);
        return savedDP;
    }

    @Override
    public DeliveryPartner getUserDetailsByEmail(String email) {
        Optional<DeliveryPartner> opt = deliveryPartnerRepository.findByEmail(email);
        if(opt.isEmpty()) throw new SwiggyException("This email not registered with us");
        DeliveryPartner deliveryPartner = opt.get();
        return deliveryPartner;
    }
}
