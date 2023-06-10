package br.com.compassuol.sp.challenge.ecommerce.controllers;

import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerRequestDTO;
import br.com.compassuol.sp.challenge.ecommerce.dtos.CustomerResponseDTO;
import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.RepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Excluir antes de subir para master
    @GetMapping("/customer")
    public List<Customer> findAll(){
        return customerService.findAllCustomer();
    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerResponseDTO> findCustomer (@PathVariable int customerId) throws CustomerNotFound {
        return customerService.findCustomer(customerId)
                .map(customer -> ResponseEntity.ok(new CustomerResponseDTO().convertToDTO(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customer")
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

    @PutMapping("/customer/{customerId}")
    public ResponseEntity<CustomerResponseDTO> changeCustomer(@PathVariable long customerId,
                                              @RequestBody @Valid CustomerRequestDTO customerRequestDTO){
        try {
            Customer changedCustomer = customerService.changeCustomer(customerId, customerRequestDTO.convertToEntity());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomerResponseDTO().convertToDTO(changedCustomer));
        } catch (RepeatedData | CustomerNotFound e) {
            throw new RuntimeException(e);
        }
    }

}
