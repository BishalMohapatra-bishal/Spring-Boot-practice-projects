package com.GlobalExceptionDemo.GlobalExceptionDemo.service;

import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerRequestDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CustomerResponseDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Customer;
import com.GlobalExceptionDemo.GlobalExceptionDemo.repository.CustomerRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponseDto creteCustomer(CustomerRequestDto customerRequestDto) {
        Customer saveCustomer = customerRepository.save(dtoToEntity(customerRequestDto));
        return entityToDto(saveCustomer);
    }

    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
        Customer updateCustomer = customerRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("No customer exist with id " + id));

        Customer updatedCustomer = customerRepository.save(dtoToEntity(customerRequestDto));
        return entityToDto(updatedCustomer);

    }

    public CustomerResponseDto getCustomerById(Long id) {
        Customer getCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Customer found with is " + id));

        return entityToDto(getCustomer);
    }

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
                customer.getPhoneNumber()
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
