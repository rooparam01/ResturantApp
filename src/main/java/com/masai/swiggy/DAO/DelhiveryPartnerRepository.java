package com.masai.swiggy.DAO;

import com.masai.swiggy.Entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelhiveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {
}
