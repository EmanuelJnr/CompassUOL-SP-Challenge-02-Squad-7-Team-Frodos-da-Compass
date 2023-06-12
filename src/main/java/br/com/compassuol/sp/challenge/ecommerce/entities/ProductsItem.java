package br.com.compassuol.sp.challenge.ecommerce.entities;

import jakarta.persistence.*;

@Entity
public class ProductsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productItemId;

    private Integer productId;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "productItemId=" + productItemId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}