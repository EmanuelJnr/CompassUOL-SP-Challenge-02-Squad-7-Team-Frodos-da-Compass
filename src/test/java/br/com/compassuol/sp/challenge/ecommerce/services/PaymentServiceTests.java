package br.com.compassuol.sp.challenge.ecommerce.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import br.com.compassuol.sp.challenge.ecommerce.entities.Payment;
import br.com.compassuol.sp.challenge.ecommerce.enums.PaymentMethod;
import br.com.compassuol.sp.challenge.ecommerce.repositories.PaymentRepository;

@SpringBootTest
public class PaymentServiceTests {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentRepository paymentRepository;

    @Test
    public void testSavePayment() {
        Payment payment = new Payment();
        payment.setOrderId(1);
        payment.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        payment.setPaymentDate("2023-06-12");
        
        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.save(payment);

        assertThat(savedPayment.getOrderId()).isEqualTo(1);
        assertThat(savedPayment.getPaymentMethod()).isEqualTo(PaymentMethod.CREDIT_CARD);
        assertThat(savedPayment.getPaymentDate()).isEqualTo("2023-06-12");
    }
    
}
