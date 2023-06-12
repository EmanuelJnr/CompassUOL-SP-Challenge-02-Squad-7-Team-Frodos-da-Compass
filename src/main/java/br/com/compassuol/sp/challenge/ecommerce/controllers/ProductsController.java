package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.ProductsDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Products;
import br.com.compassuol.sp.challenge.ecommerce.services.ProductsService;
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
public class ProductsController {
    final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "id")Integer id) {
        Optional<Products> productsOptional = productsService.findById(id);
        if (!productsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productsOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id")Integer id, @RequestBody @Valid ProductsDTO productsDTO) {
        Optional<Products> productOptional = productsService.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        var product = new Products();
        BeanUtils.copyProperties(productsDTO, product);
        product.setProductId(productOptional.get().getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productsService.save(product));
    }
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductsDTO productsDTO) {
        var product = new Products();
        BeanUtils.copyProperties(productsDTO, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")Integer id) {
        Optional<Products> productOptional = productsService.findById(id);
        if (!productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }

        productsService.delete(productOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}