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

import com.revature.katieskritters.dtos.requests.NewFishRequest;
import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Cart;

import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.services.CartService;
import com.revature.katieskritters.services.FishService;
import com.revature.katieskritters.services.JwtTokenService;
import com.revature.katieskritters.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final FishService fishService;
    private final JwtTokenService jwtTokenService;

    /*
     * @param extract token from "Authorization" header
     * 
     * @return extracted token if no exception occurs
     * 
     * @author Katie Osborne
     */
    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }
        return authHeader.substring(7);
    }

    @GetMapping
    public ResponseEntity<List<NewFishRequest>> getCartItems(HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());

        Cart cart = cartService.findByUser(user);
        List<NewFishRequest> cartFishQuantity = cart.getCartItems().stream()
                .map(item -> new NewFishRequest(item.getFish(), item.getQuantity()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(cartFishQuantity);
    }

    @PostMapping("/{fishId}/{quantity}")
    public ResponseEntity<Void> addOrUpdateItem(@PathVariable int fishId, @PathVariable int quantity,
            HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Fish fish = fishService.findById(fishId);

        cartService.addOrUpdateItem(user, fish, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{fishId}")
    public ResponseEntity<Void> removeItem(@PathVariable int fishId, HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Fish fish = fishService.findById(fishId);

        cartService.deleteItem(user, fish);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getCartTotal(HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());

        Cart cart = cartService.findByUser(user);
        double cartTotal = cart.getTotal();
        return ResponseEntity.status(HttpStatus.OK).body(cartTotal);
    }
}