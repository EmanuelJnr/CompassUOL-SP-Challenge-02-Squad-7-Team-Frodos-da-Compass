package br.com.compassuol.sp.challenge.ecommerce.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 3, message = "Name must be at least 3 characters")
    private String name;


    @Column(unique = true, nullable = false)
    @NotEmpty
    @CPF(message = "Invalid CPF. Try again.")
    private String cpf;


    @Column(unique = true, nullable = false)
    @NotEmpty
    @Email(message = "Invalid Email. Try again.")
    private String email;

    @NotNull
    private boolean active;

    public Customer() {
    }

    public Customer(String name, String cpf, String email, boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.active = active;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && active == customer.active && Objects.equals(name, customer.name) && Objects.equals(cpf, customer.cpf) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, name, cpf, email, active);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
