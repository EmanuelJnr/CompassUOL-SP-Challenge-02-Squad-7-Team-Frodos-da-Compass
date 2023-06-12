package br.com.compassuol.sp.challenge.ecommerce.controllers;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;


import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.entities.Payment;
import br.com.compassuol.sp.challenge.ecommerce.enums.PaymentMethod;
import br.com.compassuol.sp.challenge.ecommerce.enums.Status;
import br.com.compassuol.sp.challenge.ecommerce.domain.OrderService;
import br.com.compassuol.sp.challenge.ecommerce.domain.PaymentService;

@SpringBootTest
public class PaymentControllerTests {

    @Test
    public void testCreatePayment() {
        // Creating a test order
        Order order = new Order();
        order.setStatus(Status.CREATED);
        OrderService orderService = Mockito.mock(OrderService.class);
        Mockito.when(orderService.findById(1)).thenReturn(java.util.Optional.of(order));

        // Creating a test payment
        Payment payment = new Payment();
        payment.setPaymentMethod(PaymentMethod.DEBIT_CARD);
        payment.setPaymentDate("2023-06-12");
        payment.setOrderId(1);

        // Mocking PaymentService
        PaymentService paymentService = Mockito.mock(PaymentService.class);
        Mockito.when(paymentService.save(payment)).thenReturn(payment);

        // Creating a PaymentController and making the request to create the payment
        PaymentController controller = new PaymentController();
        controller.orderService = orderService;
        controller.paymentService = paymentService;
        ResponseEntity<Payment> response = controller.createPayment(payment);

        // Verifying that the code works correctly
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
        assertEquals(Status.CONFIRMED, order.getStatus());
    } 

}