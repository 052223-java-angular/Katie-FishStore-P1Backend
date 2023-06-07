package com.revature.katieskritters.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.katieskritters.dtos.requests.NewLoginRequest;
import com.revature.katieskritters.dtos.requests.NewUserRequest;
import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.services.JwtTokenService;
import com.revature.katieskritters.services.UserService;
import com.revature.katieskritters.utils.customExceptions.ResourceConflictException;
import com.revature.katieskritters.utils.customExceptions.UserNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody NewUserRequest request) {
        // check for unique username, if not throw exception
        if (!userService.isUniqueUsername(request.getUsername())) {
            throw new ResourceConflictException("Username is not unique!");
        }
        // check for valid username, if not throw exception
        if (!userService.isUsernameValid(request.getUsername())) {
            throw new ResourceConflictException("Username is not valid!");
        }
        // check if password is valid, if not throw exception
        if (!userService.isPasswordValid(request.getPassword())) {
            throw new ResourceConflictException("Password is not valid!");
        }
        // check password and confirm password are the same, if not throw exception
        if (!userService.isConfirmPasswordSame(request.getPassword(), request.getConfirmedPassword())) {
            throw new ResourceConflictException("Passwords do not match!");
        }
        // if everything checks out, register user
        userService.registerUser(request);
        // success status code for registering user
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Principal> login(@RequestBody NewLoginRequest request) {

        Principal principal = userService.loginUser(request);

        String token = tokenService.generateToken(principal);

        principal.setToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(principal);

    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<Map<String, Object>> handleResourceConflictException(ResourceConflictException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(map);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", new Date(System.currentTimeMillis()));
        map.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
    }
}