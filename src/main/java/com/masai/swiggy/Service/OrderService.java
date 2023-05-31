package com.masai.swiggy.Service;

import com.masai.swiggy.Entity.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    public Order placeNewOrder(Order order, Integer customerId, Integer restaurantId, List<Integer> menuItemIds);
    public Order deleteOrderById(Integer orderId);
    public List<Order> getOrderByCustomerId(Integer customerId);
    public Order updateOrderDetails(Order order);

    public List<Order> getAllOrders();

    public List<Order> getAllOrderByFieldSort(String field,String direction);

    public List<Order> getAllOrdersPageWise(Integer pageNumber, Integer numberOfRecords);

}
