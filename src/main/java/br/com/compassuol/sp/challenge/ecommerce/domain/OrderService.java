package br.com.compassuol.sp.challenge.ecommerce.domain;

import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public Order createOrder(Order order){
        return  orderRepository.save(order);
    }

    public Optional<Order> getOrderCustomerById(Integer customerId) {
        return orderRepository.findById(customerId);
    }
}