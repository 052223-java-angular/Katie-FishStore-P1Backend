package com.revature.katieskritters.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.repositories.CartItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItem saveOrUpdateCartItem(Cart cart, Fish fish, int quantity) {
        Optional<CartItem> optionalCartItem = findByCartAndFish(cart, fish);

        CartItem cartItem;
        if (optionalCartItem.isPresent()) {
            cartItem = optionalCartItem.get();
            cartItem.setQuantity(quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setFish(fish);
            cartItem.setQuantity(quantity);
        }

        return cartItemRepository.save(cartItem);
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public Optional<CartItem> findByCartAndFish(Cart cart, Fish fish) {
        return cartItemRepository.findByCartAndFish(cart, fish);
    }
}