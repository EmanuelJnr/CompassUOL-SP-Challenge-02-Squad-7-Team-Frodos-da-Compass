package br.com.compassuol.sp.challenge.ecommerce.domain;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.com.compassuol.sp.challenge.ecommerce.common.CustomerConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void createCustomer_WithValidData_ReturnsPlanet(){
        Customer customer = customerRepository.save(CUSTOMER);

        Customer sut = testEntityManager.find(Customer.class, customer.getCustomerId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(CUSTOMER.getName());
        assertThat(sut.getCpf()).isEqualTo(CUSTOMER.getCpf());
        assertThat(sut.getEmail()).isEqualTo(CUSTOMER.getEmail());
        assertThat(sut.isActive()).isEqualTo(CUSTOMER.isActive());
    }

    @Test
    public void createCustomer_WithInvalidData_ThrowsException(){
        Customer emptyCustomer = new Customer();
        Customer invalidCustomer = new Customer("","444444","",false);


        System.out.println(customerRepository.save(invalidCustomer));
        System.out.println(customerRepository.save(emptyCustomer));


        customerRepository.save(emptyCustomer);

        assertThatThrownBy(() -> customerRepository.save(emptyCustomer));
        assertThatThrownBy(() -> customerRepository.save(invalidCustomer));

    }


}






















