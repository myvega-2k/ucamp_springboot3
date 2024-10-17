package com.ucamp.myspringboot.repository;

import com.ucamp.myspringboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //customerId로 조회
    Optional<Customer> findByCustomerId(String custId);
    List<Customer> findByCustomerName(String custName);
}
