package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.domain.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/orders")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Object> getOrderCustomerById(@PathVariable(value = "customerId") Integer customerId){
        Optional<Order> om = orderService.getOrderCustomerById(customerId);
        if (!om.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(om.get());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        orderService.createOrder(order);
        Order createOrder = orderService.createOrder(order);
        return  new ResponseEntity<>(createOrder,HttpStatus.CREATED);
    }
}