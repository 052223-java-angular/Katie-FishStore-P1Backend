package com.revature.katieskritters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.Fish;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByCartAndFish(Cart cart, Fish fish);
}