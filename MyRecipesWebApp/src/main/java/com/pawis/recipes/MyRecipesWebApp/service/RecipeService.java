package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;

import com.pawis.recipes.MyRecipesWebApp.entity.Recipe;

public interface RecipeService {
	
	public List<Recipe> getRecipes();
	
	public Recipe addRecipe(Recipe recipe);
}
