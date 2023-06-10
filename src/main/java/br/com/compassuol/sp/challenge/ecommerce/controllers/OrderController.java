package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.entitys.OrderModel;
import br.com.compassuol.sp.challenge.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService os;

    @GetMapping("/v1/orders")
    public ResponseEntity<List<OrderModel>> getAllOrders(){
        List<OrderModel> om = os.getAllOrders();
        return  new ResponseEntity<>(om, HttpStatus.OK);
    }

    @GetMapping("v1/orders/customer/{customerId}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Long customerId){
        OrderModel om = os.getOrderById(customerId);
        if(om != null){
            return new ResponseEntity<>(om,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel om){
        OrderModel createOrder = os.createOrder(om);
        return  new ResponseEntity<>(createOrder,HttpStatus.CREATED);
    }

}
