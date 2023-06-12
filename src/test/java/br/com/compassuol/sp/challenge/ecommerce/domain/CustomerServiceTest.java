package br.com.compassuol.sp.challenge.ecommerce.domain;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerRepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.compassuol.sp.challenge.ecommerce.commom.CustomerConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer_WithValidData_ReturnsCustomer() throws CustomerRepeatedData {
        List<Customer> customerList = new ArrayList<>();
        when(customerRepository.save(CUSTOMER)).thenReturn(CUSTOMER);
        when(customerRepository.findByCpf(CUSTOMER.getCpf())).thenReturn(Optional.of(customerList));
        when(customerRepository.findByEmail(CUSTOMER.getEmail())).thenReturn(Optional.of(customerList));

        Customer sut = customerService.createCustomer(CUSTOMER);

        assertThat(sut).isEqualTo(CUSTOMER);
    }


    @Test
    public void createCustomer_WithInvalidData_ThrowsException() throws CustomerRepeatedData {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(CUSTOMER);
        when(customerRepository.findByCpf(CUSTOMER.getCpf())).thenReturn(Optional.of(customerList));
        when(customerRepository.findByEmail(CUSTOMER.getEmail())).thenReturn(Optional.of(customerList));

        assertThatThrownBy(() -> customerService.createCustomer(EMPTY_CUSTOMER));
        assertThatThrownBy(() -> customerService.createCustomer(CUSTOMER));
    }


    @Test
    public void findCustomerById_WithValidData_ReturnsCustomer() throws CustomerNotFound {
        when(customerRepository.existsById(1)).thenReturn(true);
        when(customerRepository.findById(1)).thenReturn(Optional.of(CUSTOMER));

        Optional<Customer> sut = customerService.findCustomer(1);

        assertThat(sut.get()).isEqualTo(CUSTOMER);

    }

    @Test
    public void findCustomerById_WithInvalidId_ThrowsException(){
        assertThatThrownBy(() -> customerService.findCustomer(1));
    }

    @Test
    public void changeCustomer_WithValidData_ReturnsCustomer() throws CustomerNotFound, CustomerRepeatedData {

        when(customerRepository.existsById(1)).thenReturn(true);
        when(customerRepository.findById(1)).thenReturn(Optional.of(CUSTOMER));
        when(customerRepository.findByCpf(ALTER_CUSTOMER_CPF.getCpf())).thenReturn(Optional.of(new ArrayList<>()));
        when(customerRepository.findByEmail(ALTER_CUSTOMER_CPF.getEmail())).thenReturn(Optional.of(new ArrayList<>()));
        when(customerRepository.save(ALTER_CUSTOMER_CPF)).thenReturn(ALTER_CUSTOMER_CPF);

        Customer sut = customerService.changeCustomer(1, ALTER_CUSTOMER_CPF);
        assertThat(sut).isEqualTo(ALTER_CUSTOMER_CPF);

    }

    @Test
    public void changeCustomer_WithInvalidData_ReturnsCustomer() throws CustomerNotFound, CustomerRepeatedData {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(CUSTOMER);
        customerList.add(CUSTOMER_TWO);
        when(customerRepository.existsById(1)).thenReturn(true);
        when(customerRepository.findById(1)).thenReturn(Optional.of(CUSTOMER));
        when(customerRepository.findByCpf(ALTER_CUSTOMER_CPF.getCpf())).thenReturn(Optional.of(customerList));
        when(customerRepository.findByEmail(ALTER_CUSTOMER_CPF.getEmail())).thenReturn(Optional.of(customerList));

        assertThatThrownBy(() -> customerService.changeCustomer(1, ALTER_CUSTOMER_CPF));

    }




}