package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerRequestDTO;
import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerResponseDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.RepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponseDTO> findCustomer (@PathVariable(value = "customerId") Integer customerId) throws CustomerNotFound {
        return customerService.findCustomer(customerId)
                .map(customer -> ResponseEntity.ok(new CustomerResponseDTO().convertToDTO(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO){
        Customer customer = customerRequestDTO.convertToEntity();

        try {
            Customer customerCreated = customerService.createCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomerResponseDTO().convertToDTO(customerCreated));
        } catch (RepeatedData e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("{customerId}")
    public ResponseEntity<Object> changeCustomer(@PathVariable(value = "customerId") Integer customerId,
                                                  @RequestBody @Valid CustomerRequestDTO customerRequestDTO){

        Optional<Customer> customerOptional = null;
        try {
            customerOptional = customerService.findCustomer(customerId);
        } catch (CustomerNotFound e) {
            throw new RuntimeException(e);
        }

        if (!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!");
        }

        var customer = customerOptional.get();
        customer.setName(customerRequestDTO.getName());
        customer.setCpf(customerRequestDTO.getCpf());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setActive(customerRequestDTO.isActive());
        return ResponseEntity.status(HttpStatus.OK).body(customerService.save(customer));

        /*
        try {
            Customer changedCustomer = customerService.changeCustomer(customerId, customerRequestDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomerResponseDTO().convertToDTO(changedCustomer));
        } catch (RepeatedData | CustomerNotFound e) {
            throw new RuntimeException(e);
        }*/
    }

}
