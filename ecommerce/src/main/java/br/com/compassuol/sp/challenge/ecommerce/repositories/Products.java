package br.com.compassuol.sp.challenge.ecommerce.entities;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String description;

    @NotNull
    private boolean active;
    
    public Products() {
    	
    }
    
    public Products(Integer productId, String name, double price, String description,boolean active) {
    	this.productId = productId;
    	this.name = name;
    	this.price = price;
    	this.description = description;
    }
    

	public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
    	return active;
    }
    
    public void setActive(boolean active) {
    	this.active = active;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return productId == products.productId && active == products.active && Objects.equals(name, products.name) && Objects.equals(productId, products.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name,active);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}