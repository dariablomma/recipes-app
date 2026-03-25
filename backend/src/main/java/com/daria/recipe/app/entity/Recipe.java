package com.daria.recipe.app.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
        YouTube or other platforms links, where recipe is uploaded
     */
    @Column(length = 500)
    private String externalLink;

    @Column(length = 2000)
    private String description;
}
