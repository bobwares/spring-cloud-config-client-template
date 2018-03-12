package com.bobwares.cloud.config.client.repository;


import com.bobwares.cloud.config.client.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {}