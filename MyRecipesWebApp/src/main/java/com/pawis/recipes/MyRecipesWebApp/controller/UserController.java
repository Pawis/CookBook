package com.pawis.recipes.MyRecipesWebApp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pawis.recipes.MyRecipesWebApp.dao.RoleRepository;
import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UsersRepository userRep;

	

	@GetMapping("/showNewUserForm")
	public String showNewUserRegistrationForm(@AuthenticationPrincipal AppUserDetails loggedUser, Model model) {
		Optional<User> newUser = userRep.findUserByUsername(loggedUser.getUsername());
		if(newUser.isPresent()) {
			model.addAttribute("user",newUser);
		} else {
			throw new UsernameNotFoundException("User not Found");
		}
		//User newUser = new User();
		//model.addAttribute("user", newUser);
		

		return "user/new-user-form";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") User user, @AuthenticationPrincipal AppUserDetails loggedUser) {
		
		
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		
		
		userService.UpdatUserWithRole(user);
		
		return "redirect:userList";
	}

	@PostMapping("/processNewUser")
	public String saveNewUser(@ModelAttribute("user") User user) {
		userService.saveUser(user);

		return "redirect:userList";

	}

	@GetMapping("/userList")
	public String getUsers( Model model) {

		List<User> users = userService.getUsers();
		//model.addAttribute("principal",principal.getName());
		model.addAttribute("users", users);
		//model.addAttribute("users", users);

		return "user/user-list";
	}

	@GetMapping("/updateUser")
	public String updateUser(@RequestParam("userId") int theId, Model model) {
		User user = userService.getUser(theId);
		List<Role> roles = userService.getRoles();
		model.addAttribute("user",user);
		model.addAttribute("roles",roles);

		return "user/new-user-form";
	}

	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("userId") int theId) {
		userService.deleteUser(theId);
		

		return "redirect:userList";
	}

}
