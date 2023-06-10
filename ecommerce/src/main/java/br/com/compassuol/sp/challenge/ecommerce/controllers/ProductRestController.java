package br.com.compassuol.sp.challenge.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compassuol.sp.challenge.ecommerce.entity.Product;
import br.com.compassuol.sp.challenge.ecommerce.repository.ProductDAO;
import br.com.compassuol.sp.challenge.ecommerce.service.ProductNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ProductRestController {

	
	private ProductDAO productDAO;

	@GetMapping(path = "/products")
	public List<Product> findAll(){
		return productDAO.findAll();
	}

	@GetMapping(path = "/products/(productID)")
	public Product getProduct(@PathVariable int id) {
		Product product = productDAO.findID(id);

		if(product == null) {
			throw new ProductNotFoundException("Product id not found - " + id);
		}
		return product;
	}

	@PutMapping(path = "/products")
	public Product updateProduct(@RequestBody Product product) {
		Product p = productDAO.save(product);
		return p;
	}

}
