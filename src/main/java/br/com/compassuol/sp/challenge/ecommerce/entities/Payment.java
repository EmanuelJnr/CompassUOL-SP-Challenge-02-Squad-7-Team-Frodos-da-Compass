package br.com.compassuol.sp.challenge.ecommerce.entities;

import java.util.Objects;

import java.time.LocalDateTime;

import br.com.compassuol.sp.challenge.ecommerce.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String paymentDate;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Order order;

    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Payment(Integer paymentId, PaymentMethod paymentMethod, String paymentDate, Order order, Integer orderId) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.order = order;
        this.orderId = orderId;
    }

    public Payment() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, paymentDate, paymentId, paymentMethod);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        return Objects.equals(orderId, other.orderId) && Objects.equals(paymentDate, other.paymentDate)
                && Objects.equals(paymentId, other.paymentId) && paymentMethod == other.paymentMethod;
    }

    @Override
    public String toString() {
        return "Payment [paymentId=" + paymentId + ", paymentMethod=" + paymentMethod + ", paymentDate=" + paymentDate
                + ", orderId=" + orderId + "]";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}