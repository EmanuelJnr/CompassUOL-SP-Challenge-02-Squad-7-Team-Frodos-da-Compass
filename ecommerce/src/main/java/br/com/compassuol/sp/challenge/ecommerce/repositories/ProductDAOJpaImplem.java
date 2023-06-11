package br.com.compassuol.sp.challenge.ecommerce.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.compassuol.sp.challenge.ecommerce.entity.Product;


@Repository
public class ProductDAOJpaImplem implements ProductDAO{

	private ProductDAO productDAO;
	
	@Autowired
	public ProductDAOJpaImplem(ProductDAO theProductDAO) {
		productDAO = theProductDAO;
	}

	public List<Product> findAll() {
		return productDAO.findAll();
	}

	public Product save(Product product) {
		return productDAO.save(product);
	}

	public Product findID(int id) {
		return productDAO.findID(id);
	}

}
