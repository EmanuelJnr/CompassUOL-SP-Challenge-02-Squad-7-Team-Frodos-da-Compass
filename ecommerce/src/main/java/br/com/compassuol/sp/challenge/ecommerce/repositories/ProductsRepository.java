package br.com.compassuol.sp.challenge.ecommerce.repositories;

import br.com.compassuol.sp.challenge.ecommerce.entitys.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    boolean existsByName(String name);
}