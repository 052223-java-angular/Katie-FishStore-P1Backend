package com.revature.katieskritters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.services.FishService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/fish")
public class FishController {
    private final FishService fishService;

    @GetMapping("/browse")
    public ResponseEntity<List<Fish>> browse() {
        List<Fish> fish = fishService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(fish);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Fish>> search(@PathVariable String name) {
        List<Fish> fish = fishService.searchFish(name);
        return ResponseEntity.status(HttpStatus.OK).body(fish);
    }

}


