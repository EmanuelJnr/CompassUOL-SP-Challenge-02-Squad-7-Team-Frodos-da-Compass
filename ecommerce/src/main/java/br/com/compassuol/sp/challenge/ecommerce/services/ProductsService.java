package br.com.compassuol.sp.challenge.ecommerce.services;

import br.com.compassuol.sp.challenge.ecommerce.entitys.Products;
import br.com.compassuol.sp.challenge.ecommerce.repositories.ProductsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    public boolean existsByName(String name) {
        return productsRepository.existsByName(name);
    }

    public Optional<Products> findById(int id) {
        return productsRepository.findById(id);
    }

    @Transactional
    public void delete(Products products) {
        productsRepository.delete(products);
    }
}