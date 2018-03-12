package com.bobwares.cloud.config.client.service;

import com.bobwares.cloud.config.client.dto.CustomerInDto;
import com.bobwares.cloud.config.client.entity.Customer;

import java.util.Optional;

public interface CustomerService {

    Iterable<Customer> getAll();

    Optional<Customer> get(long id);

    Customer post(CustomerInDto customerInDto);

    Optional<Customer> put(CustomerInDto customerInDto, long id);

    void delete(long id);
}
