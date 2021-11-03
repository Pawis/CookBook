package com.pawis.recipes.MyRecipesWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawis.recipes.MyRecipesWebApp.entity.Recipe;
import com.pawis.recipes.MyRecipesWebApp.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	RecipeService recipeService;
	
	@GetMapping()
	public String getRecipes(Model model) {
			
		List<Recipe> recipes = recipeService.getRecipes();
		
		model.addAttribute("recipes",recipes);
		
		return "recipes-list";
	}
	
	@GetMapping("/newRecipeForm")
	public String showRecipeForm(Model model) {
		
		Recipe newRecipe = new Recipe();
		
		model.addAttribute("recipe", newRecipe);
		return "recipe/new-recipe-form";
		
	}
	
	@PostMapping("/processNewRecipe")
	public String processNewRecipe(@ModelAttribute Recipe recipe) {
		
		
		recipeService.addRecipe(recipe);
		
		return "redirect:/recipe/recipes-list";
	}
}
