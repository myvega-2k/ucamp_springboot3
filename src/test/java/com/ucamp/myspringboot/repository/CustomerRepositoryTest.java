package com.ucamp.myspringboot.repository;

import com.ucamp.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    void save_find() {
        Customer customer = new Customer();
        customer.setCustomerId("A001");
        customer.setCustomerName("스프링");
        Customer saved = customerRepository.save(customer);
        assertThat(saved).isNotNull();
        assertThat(saved.getCustomerName()).isEqualTo("스프링");
    }
}