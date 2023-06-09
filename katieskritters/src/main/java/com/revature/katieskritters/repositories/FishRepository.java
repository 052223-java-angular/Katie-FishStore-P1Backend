package com.revature.katieskritters.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.katieskritters.entities.Fish;

public interface FishRepository extends JpaRepository<Fish, Integer> {
    List<Fish> findByNameContaining(String name);
    
    Optional<Fish> findById(Integer id);

}