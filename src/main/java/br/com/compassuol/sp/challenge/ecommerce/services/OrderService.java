package br.com.compassuol.sp.challenge.ecommerce.services;

import br.com.compassuol.sp.challenge.ecommerce.entitys.OrderModel;
import br.com.compassuol.sp.challenge.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Order;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository or;

    public List<OrderModel> getAllOrders(){
        return or.findAll();
    }

    public OrderModel getOrderById(Long id) {
        return or.findById(id).orElse(null);
    }

    public OrderModel createOrder(OrderModel order){
        return  or.save(order);
    }

}
