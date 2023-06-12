package br.com.compassuol.sp.challenge.ecommerce.domain;

import br.com.compassuol.sp.challenge.ecommerce.entities.Product;
import br.com.compassuol.sp.challenge.ecommerce.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}