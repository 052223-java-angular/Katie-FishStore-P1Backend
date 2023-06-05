package com.revature.katieskritters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
