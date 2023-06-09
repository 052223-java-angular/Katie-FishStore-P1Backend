package com.revature.katieskritters.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.repositories.CartItemRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public List<CartItem> findAllFishById(Fish fish) {
        return cartItemRepository.findAllByFish(fish);
    }

    public CartItem saveIem(CartItem cartItem, Fish fish) {
        CartItem item = new CartItem();
        item.setFish(fish);
        return cartItemRepository.save(item);
    }

    public CartItem update(CartItem cartItem) {
        CartItem updatedItem = new CartItem();
        if (cartItem != null) {
            updatedItem.setFish(cartItem.getFish());
            cartItemRepository.update(updatedItem);
            return updatedItem;
        }
        return null;
    }

    public void deleteItem(CartItem cartItem, Fish fish) {
        if (cartItem.getFish().equals(fish)) {
            cartItemRepository.delete(cartItem);
        } else {
            throw new RuntimeException("Cart item cannot be deleted.");
        }
    }
}
