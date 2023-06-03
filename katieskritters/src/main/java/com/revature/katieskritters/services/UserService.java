package com.revature.katieskritters.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.isEmpty();
    }

}
