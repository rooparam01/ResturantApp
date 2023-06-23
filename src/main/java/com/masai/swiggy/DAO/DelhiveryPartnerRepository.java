package com.masai.swiggy.DAO;

import com.masai.swiggy.Entity.Customer;
import com.masai.swiggy.Entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DelhiveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {

    public Optional<DeliveryPartner> findByEmail(String email);
}
