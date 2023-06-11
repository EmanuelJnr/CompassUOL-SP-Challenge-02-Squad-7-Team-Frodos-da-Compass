package br.com.compassuol.sp.challenge.ecommerce.entities;

import java.util.Objects;

import java.time.LocalDateTime;

import br.com.compassuol.sp.challenge.ecommerce.enums.PaymentMethod;
import jakarta.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "orderId")
    private Order orderId;

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

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Payment(Integer paymentId, PaymentMethod paymentMethod, LocalDateTime paymentDate, Order orderId) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
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

}