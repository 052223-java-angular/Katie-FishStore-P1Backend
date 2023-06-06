package com.revature.katieskritters.services;

import org.springframework.stereotype.Service;
import java.util.Optional;

import com.revature.katieskritters.entities.Role;
import com.revature.katieskritters.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        Optional<Role> roleOpt = roleRepository.findByName(name);

        if (roleOpt.isPresent()) {
        }
        return roleOpt.get();
    }
}
