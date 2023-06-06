package com.revature.katieskritters.dtos.requests;

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
public class NewUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmedPassword;
}
