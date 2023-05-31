package com.masai.swiggy.Controller;

import com.masai.swiggy.Entity.Order;
import com.masai.swiggy.Service.OrderService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders/{customerId}/{restaurantId}")
    public ResponseEntity<Order> placeNewOrder(@Valid @RequestBody Order order, @PathVariable Integer customerId, @PathVariable Integer restaurantId,@RequestParam List<Integer> menuItemIds){
        Order savedOrder = orderService.placeNewOrder(order,customerId,restaurantId,menuItemIds);
        return new ResponseEntity<Order>(savedOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable Integer orderId){
        Order deletedOrder = orderService.deleteOrderById(orderId);
        return new ResponseEntity<>(deletedOrder,HttpStatus.OK);
    }

    @PutMapping("/orders")
    public ResponseEntity<Order> updateOrderDetails(@Valid @RequestBody Order order){
        Order updatedOrder = orderService.updateOrderDetails(order);
        return new ResponseEntity<>(updatedOrder,HttpStatus.ACCEPTED);
    }

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable Integer customerId){
        List<Order> orderList = orderService.getOrderByCustomerId(customerId);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/getsortedorders/{field}")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable String field,@RequestParam(required = false) String direction){
       List<Order> orderList = orderService.getAllOrderByFieldSort(field,direction);
       return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/getorderPage/{pageNumber}")
    public ResponseEntity<List<Order>> getOrderByPage(@PathVariable Integer pageNumber,@RequestParam Integer noOfRecords){
        List<Order> orderList = orderService.getAllOrdersPageWise(pageNumber,noOfRecords);
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }
}
