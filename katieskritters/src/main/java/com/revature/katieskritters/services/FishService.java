package com.revature.katieskritters.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.dtos.requests.NewBrowseRequest;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.repositories.FishRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FishService {
    private final FishRepository fishRepository;

    public List<Fish> findAllById(NewBrowseRequest request) {
        List<Fish> fish = fishRepository.findAllById(request.getId());

        return fish;

    }
}