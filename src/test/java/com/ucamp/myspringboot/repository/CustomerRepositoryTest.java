package com.ucamp.myspringboot.repository;

import com.ucamp.myspringboot.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    //Rollback 하지 마세요
    @Rollback(value = false)
    void save_find() {
        Customer customer = new Customer();
        customer.setCustomerId("A002");
        customer.setCustomerName("스프링");
        Customer saved = customerRepository.save(customer);
        assertThat(saved).isNotNull();
        assertThat(saved.getCustomerName()).isEqualTo("스프링");
        Optional<Customer> optionalById =
                customerRepository.findById(1L);//Optional<Customer>
        //assertThat(optionalById).isNotEmpty();
        if(optionalById.isPresent()){
            Customer exist = optionalById.get();
            assertThat(exist.getCustomerId()).isEqualTo("A001");
            //update는 setter만 호출해도 업데이트가 됩니다.
            exist.setCustomerName("스프링부트");
            //delete
            //customerRepository.delete(exist);
        }



    }
}