package br.com.compassuol.sp.challenge.ecommerce.entitys;

import br.com.compassuol.sp.challenge.ecommerce.enums.StatusOrder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_Id")
    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrder so;

    public OrderModel(Long orderId, Customer customer, LocalDateTime date, StatusOrder so) {
        this.orderId = orderId;
        this.customer = customer;
        this.date = date;
        this.so = so;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public StatusOrder getSo() {
        return so;
    }

    public void setSo(StatusOrder so) {
        this.so = so;
    }
}
