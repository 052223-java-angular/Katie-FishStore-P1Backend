package com.revature.katieskritters.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    public Cart findByUser(User user) {
        Optional<Cart> optionalCart = cartRepository.findByUser(user);

        if (optionalCart.isPresent()) {
            return optionalCart.get();
        } else {
            return createNewCart(user);
        }
    }

    public Cart addOrUpdateItem(User user, Fish fish, int quantity) {
        Cart cart = findByUser(user);
        boolean updated = false;

        for (CartItem item : cart.getCartItems()) {
            if (item.getFish().equals(fish)) {
                int updatedQuantity = item.getQuantity() + quantity;
                item.setQuantity(updatedQuantity);
                cartItemService.saveOrUpdateCartItem(cart, fish, updatedQuantity);
                updated = true;
                break;
            }
        }

        if (!updated) {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setFish(fish);
            item.setQuantity(quantity);
            CartItem savedItem = cartItemService.saveOrUpdateCartItem(cart, fish, quantity);
            cart.getCartItems().add(savedItem);
        }

        cart.updateTotal();
        cartRepository.save(cart);

        return cart;
    }

    public void deleteItem(User user, Fish fish) {
        Cart cart = findByUser(user);
        CartItem cartItem = cartItemService.findByCartAndFish(cart, fish)
                .orElseThrow(() -> new RuntimeException("No cart item found to delete!"));
        cart.getCartItems().remove(cartItem);
        cart.updateTotal();
        cartRepository.save(cart);
        cartItemService.deleteCartItem(cartItem);
    }

    public Cart createNewCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    // public void clearCart(User user) {
    // this.cartRepository.clearCart(user);
    // }
}