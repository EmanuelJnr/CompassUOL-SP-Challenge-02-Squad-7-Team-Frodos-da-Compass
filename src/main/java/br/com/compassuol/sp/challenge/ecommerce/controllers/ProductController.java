package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.ProductDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Product;
import br.com.compassuol.sp.challenge.ecommerce.domain.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/products")
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "id")Integer id) {
        Optional<Product> productsOptional = productService.findById(id);
        if (!productsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")Integer id, @RequestBody @Valid ProductDTO productDTO) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        var product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        product.setProductId(productOptional.get().getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(product));
    }
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDTO productDTO) {
        if (productService.existsByName(productDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: A product with that name already exists!");
        }

        var product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")Integer id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        productService.delete(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}