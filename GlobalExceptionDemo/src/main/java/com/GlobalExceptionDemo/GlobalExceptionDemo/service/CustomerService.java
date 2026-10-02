package com.GlobalExceptionDemo.GlobalExceptionDemo.service;

import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerRequestDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerResponseDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Cart;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Customer;
import com.GlobalExceptionDemo.GlobalExceptionDemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        Customer savedCustomer = dtoToEntity(customerRequestDto);

        Cart cart = new Cart();
        cart.setCustomer(savedCustomer);
        savedCustomer.setCart(cart);

        Customer saveCustomer = customerRepository.save(savedCustomer);
        return entityToDto(saveCustomer);
    }

    @Transactional
    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Customer existingCustomer = customerRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("No customer exist with id " + id));

        existingCustomer.setName(customerRequestDto.name());
        existingCustomer.setEmail(customerRequestDto.email());
        existingCustomer.setPhoneNumber(customerRequestDto.phoneNumber());

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return entityToDto(updatedCustomer);

    }

    @Transactional(readOnly = true)
    public CustomerResponseDto getCustomerById(Long id) {
        Customer getCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Customer found with is " + id));

        return entityToDto(getCustomer);
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().map(CustomerService::entityToDto).collect(Collectors.toList());
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomer() {
        customerRepository.deleteAll();
    }

    private static CustomerResponseDto entityToDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getCart() != null ? CartService.mapToCartResponseDto(customer.getCart()) : null
        );
    }
    private static Customer dtoToEntity(CustomerRequestDto requestDto) {
        return Customer.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .phoneNumber(requestDto.phoneNumber())
                .build();
    }
}
