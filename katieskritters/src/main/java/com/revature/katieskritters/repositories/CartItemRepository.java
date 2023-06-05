package com.revature.katieskritters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

}
