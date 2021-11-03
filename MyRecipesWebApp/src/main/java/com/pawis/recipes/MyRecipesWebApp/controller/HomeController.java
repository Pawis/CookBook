package com.pawis.recipes.MyRecipesWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String mainMenu(Model model) {

		// int id = (int)session.getAttribute("userId");
		// model.addAttribute("userId",id);

		return "index";
	}

	@GetMapping("/Login")
	public String loginForm() {

		return "/Login";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "/user/register-new-user";
	}

	@PostMapping("/processRegister")
	public String processRegisterForm(@ModelAttribute User user) {
		userService.saveUser(user);

		return "redirect:userList";

	}
}
