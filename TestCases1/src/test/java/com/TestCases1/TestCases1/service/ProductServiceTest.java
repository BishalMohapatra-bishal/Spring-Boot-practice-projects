package com.TestCases1.TestCases1.service;

import com.TestCases1.TestCases1.entity.Product;
import com.TestCases1.TestCases1.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnProductWhenProductExist() {

//        Arrange
        Product product = new Product(1L, "Laptop", 50000, 10);

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

//        Act
        Product actualResult = productService.getProductById(1L);

//        Assert
        assertEquals(1L, actualResult.getId());
        assertEquals("Laptop", actualResult.getName());

//        verify(productRepository.findById(1L));
        verify(productRepository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductDoesNotExist() {
//        Arrangement
        when(productRepository.findById(99L))
                .thenReturn(Optional.empty());

//        Act
        RuntimeException exception =
                assertThrows(RuntimeException.class, () ->
                        productService.getProductById(99L)
                );

//        Assertion
        assertEquals("Product not found: 99",
                exception.getMessage());

        verify(productRepository).findById(99L);
    }

}
