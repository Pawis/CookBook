package com.pawis.recipes.MyRecipesWebApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawis.recipes.MyRecipesWebApp.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{

	
}
