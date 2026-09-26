package com.GlobalExceptionDemo.GlobalExceptionDemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean price;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
