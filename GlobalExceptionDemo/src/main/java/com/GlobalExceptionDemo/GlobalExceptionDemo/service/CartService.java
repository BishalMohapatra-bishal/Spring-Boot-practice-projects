package com.GlobalExceptionDemo.GlobalExceptionDemo.service;

import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.AddToCartRequestDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CartItemResponseDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.CartResponseDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.dto.ProductSummeryDto;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Cart;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.CartItem;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Customer;
import com.GlobalExceptionDemo.GlobalExceptionDemo.model.Product;
import com.GlobalExceptionDemo.GlobalExceptionDemo.repository.CustomerRepository;
import com.GlobalExceptionDemo.GlobalExceptionDemo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartResponseDto addItemToCart(Long id, AddToCartRequestDto addToCartRequestDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));

        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            customer.setCart(cart);
        }

        Product product = productRepository.findById(addToCartRequestDto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found with id "
                        + addToCartRequestDto.productId()));

        Optional<CartItem> existingItem = cart.getCartItemsList().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + addToCartRequestDto.quantity());
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(addToCartRequestDto.quantity());
            cart.addCartItem(newItem);
        }

        return mapToCartResponseDto(cart);
    }

    @Transactional(readOnly = true)
    public CartResponseDto getCartByCustomerId(Long customerId) {
        Customer existCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("No customer found with id " + customerId));

        Cart cart = existCustomer.getCart();
        if (cart == null) {
            throw new RuntimeException("No cart available for this id " + customerId);
        }
            return mapToCartResponseDto(cart);
    }

    @Transactional
    public CartResponseDto removeCartItem(Long customerId, Long cartItemId) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("No customer found with id " + customerId));

        Cart existingCart = existingCustomer.getCart();
        if (existingCart != null) {
            existingCart.getCartItemsList().removeIf(item -> item.getId().equals(cartItemId));
        }
        return mapToCartResponseDto(existingCart);
    }

    public static CartResponseDto mapToCartResponseDto(Cart cart) {
        List<CartItemResponseDto> itemDto = cart.getCartItemsList().stream()
                .map(item -> new CartItemResponseDto(
                        item.getId(),
                        item.getQuantity(),
                        new ProductSummeryDto(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getProduct().getPrice()
                        )
                ))
                .toList();
        return new CartResponseDto(
                cart.getId(),
                cart.getCustomer().getId(),
                cart.getCustomer().getName(),
                itemDto
        );
    }

}
