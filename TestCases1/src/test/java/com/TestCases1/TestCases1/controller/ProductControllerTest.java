package com.TestCases1.TestCases1.controller;

import com.TestCases1.TestCases1.entity.Product;
import com.TestCases1.TestCases1.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void shouldReturnProductIfProductExist() {
//        Arrangement
        Product product = new Product(1L, "Laptop", 50000 ,10);

//        Act
//        mockMvc.perform()

        )

    }
}
