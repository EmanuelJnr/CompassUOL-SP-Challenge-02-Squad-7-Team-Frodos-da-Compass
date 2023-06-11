package br.com.compassuol.sp.challenge.ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class QtdProduct {

    @Id
    private Integer productId;
    private int quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
