package com.example.demo.services.impl;

import org.modelmapper.ModelMapper;

import com.example.demo.Exception.DuplicateResourceException;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.ProductCartDto;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.payload.request.cart.CartRegistrationRequest;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CartDto addProductToCart(CartRegistrationRequest cartRegistrationRequest) {

        Cart cart = cartRepository.findById(cartRegistrationRequest.cartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));

        Product product = productRepository.findById(cartRegistrationRequest.productId())
                .orElseThrow(()-> new ResourceNotFoundException("Product not found!"));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(
                cartRegistrationRequest.cartId(),
                cartRegistrationRequest.productId());

        if(cartItem != null){
            throw new DuplicateResourceException(product.getName() + " already exist in the cart.");
        }

        if(product.getQuantity() == 0){
            throw new ResourceNotFoundException(product.getName() + " is not available on the stock!");
        }

        if (product.getQuantity() < cartRegistrationRequest.quantity()) {
            throw new ResourceNotFoundException("Please, make an order of the " + product.getName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
        }

        CartItem newCartItem = new CartItem();

        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());
        newCartItem.setQuantity(cartRegistrationRequest.quantity());

        cartItemRepository.save(newCartItem);

        product.setQuantity(product.getQuantity() - cartRegistrationRequest.quantity());

        Double totalPrice = cart.getTotalPrice() + (product.getPrice() * cartRegistrationRequest.quantity());

        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);

        CartDto cartDto = modelMapper.map(cart, CartDto.class);

        List<ProductCartDto> productDtos = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), ProductCartDto.class)).collect(Collectors.toList());

        cartDto.setProducts(productDtos);

        return cartDto;
    }
}
