package com.daria.recipe.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy =  "user")
    private List<Recipe> recipes = new ArrayList<>();
}
