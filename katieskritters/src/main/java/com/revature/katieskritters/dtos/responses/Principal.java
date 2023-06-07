package com.revature.katieskritters.dtos.responses;

import org.apache.catalina.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Principal {
    private String id;
    private String username;
    private String role;
    private String token;

    public Principal(User user) {
        this.id = user.getUser_id();
        this.id = user.getFullName();
        this.username = user.getUsername();
        this.role = user.getRole().getName();

    }
}
