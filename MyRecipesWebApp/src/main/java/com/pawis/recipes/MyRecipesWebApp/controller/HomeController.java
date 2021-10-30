package com.pawis.recipes.MyRecipesWebApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	private String mainMenu() {
		return "main-menu";
	}
}
