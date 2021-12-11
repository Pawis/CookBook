package com.pawis.recipes.MyRecipesWebApp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pawis.recipes.MyRecipesWebApp.entity.UserDTO;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserServiceImpl userSErvice;
	
	
	@GetMapping("/users")
	public List<UserDTO> getUsers() {
		
		return userSErvice.getUserDTOs();
	}
	
	@GetMapping("/users/{userId}")
	public UserDTO getUser(@PathVariable int userId) {
		
		return userSErvice.getSingleUserDTO(userId);
	}
	
	
}

