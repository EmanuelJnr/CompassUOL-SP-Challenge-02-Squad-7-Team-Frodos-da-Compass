package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.services.CustomerService;
import br.com.compassuol.sp.challenge.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerService customerService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Object> getOrderCustomerById(@PathVariable(value = "customerId") Integer customerId){
        try {
            Optional<Customer> customer = customerService.findCustomer(customerId);
        } catch (CustomerNotFound e) {
            throw new RuntimeException(e);
        }

        List<Order> om = orderService.getOrderByIdCustomer(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(om);
    }

    public Order findById(Integer orderId){
        return orderService.findById(orderId).get();
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        Order createOrder = orderService.save(order);
        return new ResponseEntity<>(createOrder,HttpStatus.CREATED);
    }
}