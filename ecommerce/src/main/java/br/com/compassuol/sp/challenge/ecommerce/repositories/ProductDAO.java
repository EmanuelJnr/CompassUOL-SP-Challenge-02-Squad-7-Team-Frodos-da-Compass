package br.com.compassuol.sp.challenge.ecommerce.repository;

import java.util.List;

import br.com.compassuol.sp.challenge.ecommerce.entity.Product;

public interface ProductDAO {

	public List<Product> findAll();
	
	public Product save(Product product);
	
	public Product findID(int id);
}