package com.revature.katieskritters.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String role_id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> users;
}