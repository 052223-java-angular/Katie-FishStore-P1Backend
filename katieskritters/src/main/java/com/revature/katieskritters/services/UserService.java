package com.revature.katieskritters.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.revature.katieskritters.dtos.requests.NewUserRequest;
import com.revature.katieskritters.entities.Role;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public User register(String username, String password) {
        Role role = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashed, role.getRole_id());
        return userRepository.save(user);
    }

    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$");
    }

    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean isConfirmPasswordSame(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }
}
