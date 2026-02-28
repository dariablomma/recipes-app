package com.daria.recipe.app.repository;

import com.daria.recipe.app.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r JOIN FETCH r.category  WHERE  r.id = :id")
    Optional<Recipe> findByIdWithCategory(@Param("id") Long id);

    @Query("""
        SELECT r FROM Recipe r 
        JOIN FETCH r.category 
        JOIN FETCH r.user    
        WHERE  r.id = :id
    """)
    Optional<Recipe> findByIdWithCategoryAndUser(@Param("id") Long id);
}
