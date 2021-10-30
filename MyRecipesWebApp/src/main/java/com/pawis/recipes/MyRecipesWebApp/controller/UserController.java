package com.pawis.recipes.MyRecipesWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/showNewUserForm")
	public String showNewUserRegistrationForm(Model model) {

		User newUser = new User();
		model.addAttribute("user", newUser);

		return "user/new-user-form";
	}

	@PostMapping("/processNewUser")
	public String saveNewUser(@ModelAttribute("user") User user) {

		userService.saveUser(user);

		return "redirect:userList";

	}

	@GetMapping("/userList")
	public String getUsers(Model model) {

		List<User> users = userService.getUsers();
		model.addAttribute("users", users);

		return "user/user-list";
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int theId, Model model) {

		User user = userService.getUser(theId);
		model.addAttribute(user);

		return "user/new-user-form";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int theId) {
		userService.deleteUser(theId);
		
		return "redirect:userList";
	}

}
