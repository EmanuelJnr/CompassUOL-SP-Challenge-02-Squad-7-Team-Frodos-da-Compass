package br.com.compassuol.sp.challenge.ecommerce.services;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.entities.Order;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Order> getOrderByIdCustomer(Integer customerId) {
        List<Order> orders = getAllOrders();
        List<Order> resultado = new ArrayList<>();

        for(Order o: orders){
            if(o.getCustomerId() == customerId){
                resultado.add(o);
            }
        }
        return resultado;
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductsService productsService;

    public Order save(Order order) {

        Optional<Customer> customer = null;
        try {
            customer = customerService.findCustomer(order.getCustomerId());
        } catch (CustomerNotFound e) {
            throw new RuntimeException(e);
        }
        order.setCustomer(customer.get());

        return orderRepository.save(order);
    }

    public Optional<Order> findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }
}