package br.com.compassuol.sp.challenge.ecommerce.services;

import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerRepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> findCustomer(Integer customerId) throws CustomerNotFound {
        if (!customerRepository.existsById(customerId)){
            throw new CustomerNotFound(customerId);
        }

        return customerRepository.findById(customerId);
    }

    public Customer createCustomer(Customer newCustomer) throws CustomerRepeatedData {

        Optional<List<Customer>> findByCpf = customerRepository.findByCpf(newCustomer.getCpf());
        Optional<List<Customer>> findByEmail = customerRepository.findByEmail(newCustomer.getEmail());

        if (findByEmail.get().size() >= 1 ||
                findByCpf.get().size() >= 1){
            throw new CustomerRepeatedData();
        }

        return customerRepository.save(newCustomer);

    }

    public Customer changeCustomer(Integer customerId, Customer alterCustomer) throws CustomerRepeatedData,
            CustomerNotFound {

        Optional<Customer> customer = findCustomer(customerId);

        Optional<List<Customer>> findByCpf = customerRepository.findByCpf(alterCustomer.getCpf());
        findByCpf.get().remove(customer.get());

        Optional<List<Customer>> findByEmail = customerRepository.findByEmail(alterCustomer.getEmail());
        findByEmail.get().remove(customer.get());

        if (findByEmail.get().size() >= 1 ||
                findByCpf.get().size() >= 1){
            throw new CustomerRepeatedData();
        }

        alterCustomer.setCustomerId(customerId);

        return customerRepository.save(alterCustomer);
    }

}