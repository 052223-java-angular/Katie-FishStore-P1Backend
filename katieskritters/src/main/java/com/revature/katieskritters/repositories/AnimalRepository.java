package com.revature.katieskritters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {
    Animal findAllById(String animal_id);

    Animal findByName(String name);

    Animal findBySpecies(String species);

    Animal findByBreed(String breed);

    Animal findByGender(String gender);

    Animal findByPrice(int price);
}