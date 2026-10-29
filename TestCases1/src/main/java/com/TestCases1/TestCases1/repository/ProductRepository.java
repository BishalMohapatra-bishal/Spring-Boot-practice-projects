package com.TestCases1.TestCases1.repository;

import com.TestCases1.TestCases1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
