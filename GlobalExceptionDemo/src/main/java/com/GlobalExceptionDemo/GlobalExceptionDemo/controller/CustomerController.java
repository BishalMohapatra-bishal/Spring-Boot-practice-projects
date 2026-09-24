package com.GlobalExceptionDemo.GlobalExceptionDemo.controller;

import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerRequestDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerResponseDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.creteCustomer(customerRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomer(id, customerRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCustomer() {
        customerService.deleteAllCustomer();
        return ResponseEntity.noContent().build();
    }
}
//http://localhost:8080/swagger-ui/index.html