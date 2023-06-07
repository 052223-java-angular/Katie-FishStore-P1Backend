package com.revature.katieskritters.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.dtos.requests.NewBrowseRequest;
import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.services.FishService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/fish")
public class FishController {
    private final FishService fishService;

    @GetMapping("/browse")
    public ResponseEntity<List<Fish>> browse(@RequestBody NewBrowseRequest request) {
        // return all fish
        List<Fish> fish = fishService.findAllById(request);
        // return okay status
        return ResponseEntity.status(HttpStatus.OK).body(fish);
    }

    // @GetMapping("/search")
}
