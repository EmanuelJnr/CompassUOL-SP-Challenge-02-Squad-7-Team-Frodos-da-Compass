package br.com.compassuol.sp.challenge.ecommerce.services;


import br.com.compassuol.sp.challenge.ecommerce.entities.Customer;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.CustomerNotFound;
import br.com.compassuol.sp.challenge.ecommerce.exceptions.RepeatedData;
import br.com.compassuol.sp.challenge.ecommerce.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
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

    public List<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomer(Integer customerId) throws CustomerNotFound {
        if (!customerRepository.existsById(customerId)){
            throw new CustomerNotFound(customerId);
        }

        return customerRepository.findById(customerId);
    }
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer createCustomer(Customer newCustomer) throws RepeatedData {

        Optional<List<Customer>> findByCpf = customerRepository.findByCpf(newCustomer.getCpf());
        Optional<List<Customer>> findByEmail = customerRepository.findByEmail(newCustomer.getEmail());

        if (findByEmail.get().size() >= 1 ||
                    findByCpf.get().size() >= 1){
                throw new RepeatedData();
        }

        return customerRepository.save(newCustomer);

    }

    public Customer changeCustomer(Integer customerId, Customer alterCustomer) throws RepeatedData, CustomerNotFound {
        Optional<Customer> customer = findCustomer(customerId);

        Optional<List<Customer>> findByCpf = customerRepository.findByCpf(alterCustomer.getCpf());
        findByCpf.get().remove(customer.get());

        Optional<List<Customer>> findByEmail = customerRepository.findByEmail(alterCustomer.getEmail());
        findByEmail.get().remove(customer.get());

        if (findByEmail.get().size() >= 1 ||
                findByCpf.get().size() >= 1){
            throw new RepeatedData();
        }

        alterCustomer.setCustomerId(customerId);

        return customerRepository.save(alterCustomer);
    }

}
