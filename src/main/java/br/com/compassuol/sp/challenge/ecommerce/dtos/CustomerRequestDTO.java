package br.com.compassuol.sp.challenge.ecommerce.dtos;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class CustomerRequestDTO {

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

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String name, String cpf, String email, boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.active = active;
    }

    public Customer convertToEntity(){
        return new Customer(this.name,this.cpf,this.email,this.active);
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

}
