package com.daria.recipe.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String userName; // login name

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 200)
    private String email;

    @Column(length = 200)
    private String firstName;

    @Column(length = 200)
    private String lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Recipe> recipes = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    private Instant deletedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public boolean isDeleted() {
        return deletedAt != null;
    }
}
