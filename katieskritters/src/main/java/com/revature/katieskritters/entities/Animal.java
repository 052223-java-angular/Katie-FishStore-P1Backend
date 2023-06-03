package com.revature.katieskritters.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "animals")
public class Animal {
    @Id
    private String animal_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private int price;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<OrderItem> orderItem;

    @OneToOne(mappedBy = "animal")
    @JsonManagedReference
    private Favorite favorite;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CartItem> cartItem;

}
