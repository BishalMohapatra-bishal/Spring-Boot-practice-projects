package com.GlobalExceptionDemo.GlobalExceptionDemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "customer_id", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Cart cart;

    public void setCart(Cart cart) {
        this.cart = cart;
        if (cart != null) {
            cart.setCustomer(this);
        }
    }
}