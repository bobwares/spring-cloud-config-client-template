package com.bobwares.cloud.config.client.service;

import com.bobwares.cloud.config.client.dto.CustomerInDto;
import com.bobwares.cloud.config.client.entity.Customer;
import com.bobwares.cloud.config.client.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Iterable<Customer> getAll() {
        LOGGER.debug("getAll executed");
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> get(long id) {
        LOGGER.debug("Paramter id: " + id);
        return customerRepository.findById(id);
    }

    @Override
    public Customer post(CustomerInDto customerInDto) {
        Customer customer = Customer.builder()
                .firstName(customerInDto.getFirstName())
                .lastName(customerInDto.getLastName())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> put(CustomerInDto customerInDto, long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setFirstName(customerInDto.getFirstName());
            customer.setLastName(customerInDto.getLastName());
            return Optional.of(customerRepository.save(customer));
        } else {
            return customerOptional;
        }
   }

    @Override
    public void delete(long id) {
        customerRepository.deleteById(id);
    }
}
