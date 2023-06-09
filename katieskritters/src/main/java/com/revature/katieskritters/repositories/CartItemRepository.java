package com.revature.katieskritters.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Fish;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findAllByFish(Fish fish);

    CartItem update(CartItem updatedItem);
}
