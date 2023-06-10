package br.com.compassuol.sp.challenge.ecommerce.repositories;

import br.com.compassuol.sp.challenge.ecommerce.entitys.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Long> {

}
