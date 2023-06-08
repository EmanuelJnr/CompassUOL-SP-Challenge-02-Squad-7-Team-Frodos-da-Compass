package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.ProductsDTO;
import br.com.compassuol.sp.challenge.ecommerce.entitys.Products;
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

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductsDTO productsDTO) {
        if (productsService.existsByName(productsDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: A product with that name already exists!");
        }

        var products = new Products();
        BeanUtils.copyProperties(productsDTO, products);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.save(products));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id")int id) {
        Optional<Products> productsOptional = productsService.findById(id);
        if (!productsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
        }
        productsService.delete(productsOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}