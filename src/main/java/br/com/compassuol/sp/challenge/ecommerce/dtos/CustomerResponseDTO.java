package br.com.compassuol.sp.challenge.ecommerce.dtos;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;

public class CustomerResponseDTO {

    private String name;
    private String cpf;
    private String email;
    private boolean active;

    public CustomerResponseDTO() {
    }

    public CustomerResponseDTO(String name, String cpf, String email, boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.active = active;
    }

    public CustomerResponseDTO convertToDTO(Customer customer){
        return new CustomerResponseDTO(customer.getName(), customer.getCpf(), customer.getEmail(), customer.isActive());
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
