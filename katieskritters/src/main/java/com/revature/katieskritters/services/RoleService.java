package com.revature.katieskritters.services;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import com.revature.katieskritters.entities.Role;
import com.revature.katieskritters.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        Optional<Role> roleOpt = roleRepository.findByName(name);
        
        // Throw a custom exception if the role with a given name is not found
        if (roleOpt.isEmpty()) {
            throw new NoSuchElementException("No Role found with the name: " + name);
        }
        return roleOpt.get();
    }
    public Role createUserRoleIfNotExists(String roleName) {
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        
        if (existingRole.isPresent()) {
            return existingRole.get();
        } else {
            Role userRole = new Role();
            userRole.setRole_id(UUID.randomUUID().toString());
            userRole.setName(roleName);
            return roleRepository.save(userRole);
        }
    }
}



