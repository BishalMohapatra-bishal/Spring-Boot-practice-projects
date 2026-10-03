package com.GlobalExceptionDemo.GlobalExceptionDemo.repository;

import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
