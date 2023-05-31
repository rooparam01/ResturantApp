package com.masai.swiggy.DAO;

import com.masai.swiggy.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>, PagingAndSortingRepository<Order,Integer> {
    @Query("select o from Order o where o.customer.customerId=?1")
    public List<Order> getAllOrderByCustomerId(Integer customerId);

    @Query("select o from Order o")
    public List<Order> getAllOrder();


}
