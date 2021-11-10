package com.pawis.recipes.MyRecipesWebApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawis.recipes.MyRecipesWebApp.entity.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	/*
	@GetMapping("/searchUsers")
	public String searchUser(@PathVariable String findBy, Model model) {
		System.out.println(findBy);
		List<User> users = userService.searchUsersBy(findBy);
		model.addAttribute("users", users);

		return "user/user-list";
	}
	*/

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int theId, Model model) {
		User user = userService.getUser(theId);
		List<Role> roles = userService.getRoles();
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);

		return "user/update-user-form";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, @AuthenticationPrincipal AppUserDetails loggedUser) {

		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		AppUserDetails auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (auth.getId() != user.getId())
			userService.UpdatUserWithRole(user);

		return "redirect:userList";
	}

	@GetMapping("/userList")
	public String getUsers(Model model, @Param("keyword") String keyword) {

			List<User> users = userService.getUsers(keyword);
			model.addAttribute("users", users);
		

		return "user/user-list";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int theId) {
		userService.deleteUser(theId);

		return "redirect:userList";
	}

}
