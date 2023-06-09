package com.revature.katieskritters.controllers;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Favorite;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.services.FavoriteService;
import com.revature.katieskritters.services.FishService;
import com.revature.katieskritters.services.JwtTokenService;
import com.revature.katieskritters.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final UserService userService;
    private final FishService fishService;
    private final JwtTokenService jwtTokenService;

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }
        return authHeader.substring(7);
    }

    @GetMapping
    public ResponseEntity<List<Fish>> getFavorites(HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());

        List<Favorite> favorites = favoriteService.findAllFavoritesByUser(user);
        List<Fish> favoriteFish = favorites.stream().map(Favorite::getFish).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(favoriteFish);
    }

    @PostMapping("/{fishId}")
    public ResponseEntity<Void> addFavorite(@PathVariable int fishId, HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Fish fish = fishService.findById(fishId);

        favoriteService.saveFavorite(user, fish);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{fishId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable int fishId, HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Fish fish = fishService.findById(fishId);

        favoriteService.deleteFavorite(user, fish);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
