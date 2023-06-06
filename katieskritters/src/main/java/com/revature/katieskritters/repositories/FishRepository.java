package com.revature.katieskritters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {
    Fish findAllById(String animal_id);

    Fish findByType(String name);

    Fish findByName(String species);

    Fish findByGender(String gender);

    Fish findByPrice(int price);
}