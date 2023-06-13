package com.revature.katieskritters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findByUser(User user);

    // void clearCart(User user);
}