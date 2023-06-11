package br.com.compassuol.sp.challenge.ecommerce.repositories;

import br.com.compassuol.sp.challenge.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);
}