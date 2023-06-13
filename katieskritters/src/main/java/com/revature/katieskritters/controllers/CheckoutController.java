package com.revature.katieskritters.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.dtos.requests.NewPaymentRequest;
import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Cart;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.services.CartService;
import com.revature.katieskritters.services.JwtTokenService;
import com.revature.katieskritters.services.PaymentService;
import com.revature.katieskritters.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CartService cartService;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final PaymentService paymentService;

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid token");
        }
        return authHeader.substring(7);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody NewPaymentRequest paymentRequest, HttpServletRequest request) {
        String token = extractToken(request);
        Principal principal = jwtTokenService.parseToken(token);
        User user = userService.findById(principal.getId());
        Cart cart = cartService.findByUser(user);

        if (cart != null) {
            boolean paymentSuccess = paymentService.processPayment(paymentRequest);
            if (paymentSuccess) {
                cartService.createNewCart(user);
                return ResponseEntity.status(HttpStatus.OK).body("Payment was successful!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty.");
        }
    }
}