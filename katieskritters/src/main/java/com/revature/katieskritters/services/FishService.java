package com.revature.katieskritters.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.repositories.FishRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FishService {
    private final FishRepository fishRepository;

    public List<Fish> findAll() {
        List<Fish> fish = fishRepository.findAll();
        return fish;
    }

    public List<Fish> searchFish(String name) {
        List<Fish> fish = fishRepository.findByNameContaining(name);
        return fish;
    }

    public Fish findById(int id) {
        Optional<Fish> optionalFish = fishRepository.findById(id);
        
        if (optionalFish.isPresent()) {
            return optionalFish.get();
        } else {
            throw new RuntimeException("Fish not found!");
        }
    }



}
