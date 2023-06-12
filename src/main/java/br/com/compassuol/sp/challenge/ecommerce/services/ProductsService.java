package br.com.compassuol.sp.challenge.ecommerce.services;

import br.com.compassuol.sp.challenge.ecommerce.entities.Products;
import br.com.compassuol.sp.challenge.ecommerce.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Transactional
    public Products save(Products products) {
        return productsRepository.save(products);
    }

    public Optional<Products> findById(Integer id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public void delete(Products products) {
        productsRepository.delete(products);
    }

    public List<Products> findAll() {
        return productsRepository.findAll();
    }
}