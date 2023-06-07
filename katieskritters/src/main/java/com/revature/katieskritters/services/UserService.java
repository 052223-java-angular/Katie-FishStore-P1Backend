package com.revature.katieskritters.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.PrincipalMethodArgumentResolver;

import com.revature.katieskritters.dtos.requests.NewLoginRequest;
import com.revature.katieskritters.dtos.requests.NewUserRequest;
import com.revature.katieskritters.dtos.responses.Principal;
import com.revature.katieskritters.entities.Role;
import com.revature.katieskritters.entities.User;
import com.revature.katieskritters.repositories.UserRepository;
import com.revature.katieskritters.utils.customExceptions.UserNotFoundException;

import java.util.regex.Pattern;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public User registerUser(NewUserRequest request) {
        Role role = roleService.findByName("USER");
        String hashed = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        // String user_id = UUID.randomUUID().toString();
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getUsername(),
                hashed, role);
        userRepository.save(user);
        return user;
    }

    public Principal loginUser(NewLoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isPresent()) {
            User userLogin = userOpt.get();
            if (BCrypt.checkpw(request.getPassword(), userLogin.getPassword())) {
                return new Principal(userLogin);
            }
        }

        throw new UserNotFoundException("Invalid credentials!");
    }

    // checks for email validation
    public boolean isEmailValid(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        if (email == null) {
            return false;
        }
        return pattern.matcher(regex).matches();
    }

    // check for unique username
    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            return true;
        }
        return false;
    }

    // checks for username validation
    public boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$");
    }

    // checks for password validation
    public boolean isPasswordValid(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    // confirms passwords are entered correctly twice
    public boolean isConfirmPasswordSame(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }
}
