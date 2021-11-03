package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawis.recipes.MyRecipesWebApp.dao.RecipeRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;

	@Override
	@Transactional
	public List<Recipe> getRecipes() {

		List<Recipe> recipes;
		recipes = recipeRepo.findAll();

		return recipes;
	}

	@Override
	@Transactional
	public Recipe addRecipe(Recipe recipe) {
		
		recipeRepo.save(recipe);
		
		return recipe;
	}

}
