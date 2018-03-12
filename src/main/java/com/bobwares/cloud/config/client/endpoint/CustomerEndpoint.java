package com.bobwares.cloud.config.client.endpoint;

import com.bobwares.cloud.config.client.dto.CustomerInDto;
import com.bobwares.cloud.config.client.entity.Customer;
import com.bobwares.cloud.config.client.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RefreshScope
@RestController
public class CustomerEndpoint {

    @Value("${info.foo}")
    private String foo;

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("foo")
    public String getFoo() {
        return foo;
    }

    @GetMapping("customer")
    public Iterable<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<Customer> get(@PathVariable("id") long id) {
        Optional<Customer> optional = customerService.get(id);
        return optional
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("customer")
    public Customer post(@RequestBody CustomerInDto customerInDto) {
        return customerService.post(customerInDto);
    }

    @PutMapping("customer/{id}")
    public ResponseEntity<Customer> put(@PathVariable("id") long id, @RequestBody CustomerInDto customerInDto) {
        Optional<Customer> optional = customerService.put(customerInDto, id);
        return optional.map(customer -> new ResponseEntity<>(optional.get(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND) );
    }

    @DeleteMapping("customer/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        customerService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
