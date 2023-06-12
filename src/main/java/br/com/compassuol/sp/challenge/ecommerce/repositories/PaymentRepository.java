package br.com.compassuol.sp.challenge.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compassuol.sp.challenge.ecommerce.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
