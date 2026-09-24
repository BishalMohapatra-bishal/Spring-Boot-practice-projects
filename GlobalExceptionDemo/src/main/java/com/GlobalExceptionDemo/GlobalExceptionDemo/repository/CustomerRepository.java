package com.GlobalExceptionDemo.GlobalExceptionDemo.repository;

import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
