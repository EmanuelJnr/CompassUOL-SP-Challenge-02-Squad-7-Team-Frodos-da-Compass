package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerRequestDTO;
import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerResponseDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerRepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.domain.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerResponseDTO> findCustomer (@PathVariable(value = "customerId") Integer customerId)
            throws CustomerNotFound {

        return customerService.findCustomer(customerId)
                .map(customer -> ResponseEntity.ok(new CustomerResponseDTO().convertToDTO(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerRequestDTO customerRequestDTO){

        try {
            Customer customerCreated = customerService.createCustomer(customerRequestDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CustomerResponseDTO().convertToDTO(customerCreated));
        } catch (CustomerRepeatedData e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("{customerId}")
    public ResponseEntity<Object> changeCustomer(@PathVariable(value = "customerId") Integer customerId,
                                                 @RequestBody @Valid CustomerRequestDTO customerRequestDTO){

        try {
            Customer changedCustomer = customerService.changeCustomer(customerId, customerRequestDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponseDTO().convertToDTO(changedCustomer));
        } catch (CustomerRepeatedData | CustomerNotFound e) {
            throw new RuntimeException(e);
        }
    }

}