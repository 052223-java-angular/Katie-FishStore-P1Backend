package com.revature.katieskritters.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.CartItem;
import com.revature.katieskritters.entities.Favorite;
import com.revature.katieskritters.entities.Fish;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.CartItemRepository;
import com.revature.katieskritters.services.CartItemService;
import com.revature.katieskritters.services.FishService;
import com.revature.katieskritters.services.JwtTokenService;
import com.revature.katieskritters.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final FishService fishService;
    private final CartItemService cartItemService;

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }
        return authHeader.substring(7);
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> findAllCartItems(HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());

        List<CartItem> cartItems = cartItemService.findAllCartItemsByUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(cartItems);
    }

    @PostMapping("/item/{fish_id}")
    public ResponseEntity<Void> addItem(@PathVariable int fish_id, @RequestBody CartItem cartItem,
            HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Fish fish = fishService.findById(fish_id);

        cartItemService.saveIem(cartItem, fish);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/item/{cartItemId}/quantity/{quantity}")
    public ResponseEntity<Void> updateQuantity(@PathVariable String cartItem_id, @PathVariable int quantity) {
        CartItem cartItem = cartItemService.findById(cartItem_id);
        cartItem.setQuantity(quantity);
        cartItem.saveItem(cartItem);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable String cartItem_id) {
        CartItem cartItem = cartItemService.findById(cartItem_id);
        cartItemService.deleteItem(cartItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
