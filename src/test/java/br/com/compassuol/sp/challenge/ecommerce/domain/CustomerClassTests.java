package br.com.compassuol.sp.challenge.ecommerce.domain;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.RepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.repositories.CustomerRepository;
import br.com.compassuol.sp.challenge.ecommerce.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.compassuol.sp.challenge.ecommerce.common.CustomerConstants.CUSTOMER;
import static br.com.compassuol.sp.challenge.ecommerce.common.CustomerConstants.INVALID_CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerClassTests {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void createCustomer_WithValidData_ReturnsCustomer() throws RepeatedData {
        when(customerRepository.save(CUSTOMER)).thenReturn(CUSTOMER);

        Customer sut = customerService.createCustomer(CUSTOMER);

        assertThat(sut).isEqualTo(CUSTOMER);
    }

    @Test
    public void createCustomer_WithInvalidData_ThrowsException(){
        when(customerRepository.save(INVALID_CUSTOMER)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> customerService.createCustomer(INVALID_CUSTOMER)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getCustomer_ByExistingId_ReturnsCustomer() throws CustomerNotFound {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(CUSTOMER));

        Optional<Customer> sut = customerService.findCustomer(1l);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(CUSTOMER);
    }

    @Test
    public void getCustomer_ByUnexistingId_ReturnsEmpty() throws CustomerNotFound {
        when(customerRepository.findById(1l)).thenReturn(Optional.empty());

        Optional<Customer> sut = customerService.findCustomer(1l);

        assertThat(sut).isEmpty();

    }

}
