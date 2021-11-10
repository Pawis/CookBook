package com.pawis.recipes.MyRecipesWebApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_details")
public class RecipeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "recipe_instructions")
	private String recipeInstructions;

	public RecipeDetails() {

	}

	public RecipeDetails(int id, String recipeInstructions) {
		this.id = id;
		this.recipeInstructions = recipeInstructions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecipeInstructions() {
		return recipeInstructions;
	}

	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}

}
