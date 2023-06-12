package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.enums.Status;
import br.com.compassuol.sp.challenge.ecommerce.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.sp.challenge.ecommerce.entities.Payment;
import br.com.compassuol.sp.challenge.ecommerce.services.PaymentService;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

    @Autowired PaymentService paymentService;

    @Autowired 
    OrderService orderService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        Order order = orderService.findById(payment.getOrderId()).get();
        payment.setOrder(order);
        payment.getOrder().setStatus(Status.CONFIRMED);
        Payment newPayment = paymentService.save(payment);
        return ResponseEntity.ok(newPayment);
    }

}