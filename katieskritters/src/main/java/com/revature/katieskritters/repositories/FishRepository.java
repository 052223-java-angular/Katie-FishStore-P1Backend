package com.revature.katieskritters.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.katieskritters.entities.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, String> {

    List<Fish> findAllById(String id);

}
