package com.revature.katieskritters.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {

}
