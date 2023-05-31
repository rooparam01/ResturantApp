package com.masai.swiggy.Service;

import com.masai.swiggy.DAO.CustomerRepository;
import com.masai.swiggy.DAO.MenuItemRepository;
import com.masai.swiggy.DAO.OrderRepository;
import com.masai.swiggy.DAO.RestaurantRepository;
import com.masai.swiggy.Entity.*;
import com.masai.swiggy.Exception.SwiggyException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MenuItemRepository menuItemRepository;

    @Override
    public Order placeNewOrder(Order order, Integer customerId, Integer restaurantId, List<Integer> menuItemIds) {
        Double totalBill = 0.0;
        for(Integer ids:menuItemIds){
            MenuItem menuItem = menuItemRepository.findById(ids).orElseThrow(()->new SwiggyException("Invalid menu Item"));
            totalBill+=menuItem.getPrice();
            order.getMenuItemList().add(menuItem);
        }
        order.setBillAmount(totalBill);
        order.setOrderStatus(OrderStatus.PREPAREING);
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->new SwiggyException("Customer Not Found"));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new SwiggyException("Restaurand Not Found"));
        customer.getOrderList().add(order);
        restaurant.getOrderList().add(order);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);

        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    @Override
    public Order deleteOrderById(Integer orderId) {
        Order orderforDelete = orderRepository.findById(orderId).orElseThrow(()->new SwiggyException("Invalid Order Id"));
        orderforDelete.setCustomer(null);
        orderforDelete.setRestaurant(null);
        orderforDelete.setDeliveryPartner(null);
        orderforDelete.setMenuItemList(null);
       orderRepository.delete(orderforDelete);
       return orderforDelete;
    }

    @Override
    public List<Order> getOrderByCustomerId(Integer customerId) {
       boolean customerisPresenet = customerRepository.existsById(customerId);
       if(!customerisPresenet) throw new SwiggyException("Invalid Customer Id");
        List<Order> orderList = orderRepository.getAllOrderByCustomerId(customerId);
        if(orderList.isEmpty()) throw new SwiggyException("No order found By this Customer Id");
        return orderList;
    }

    @Override
    public Order updateOrderDetails(Order order) {
        if(!orderRepository.existsById(order.getOrderId())){
            throw new SwiggyException("Invalid Order Id");
        }
        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.getAllOrder();
        if(orderList.isEmpty()) throw new SwiggyException("There Is no order Available");
        return orderList;
    }

    @Override
    public List<Order> getAllOrderByFieldSort(String field, String direction) {
    Sort sort = direction.equals("asc")? Sort.by(field).ascending():Sort.by(field).descending();
    List<Order> orderList = orderRepository.findAll(sort);
    return orderList;
    }

    @Override
    public List<Order> getAllOrdersPageWise(Integer pageNumber, Integer numberOfRecords) {
      Pageable pg = PageRequest.of(pageNumber-1,numberOfRecords);
      List<Order> orderList = orderRepository.findAll(pg).getContent();
      return orderList;
    }
}
