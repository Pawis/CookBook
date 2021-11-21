package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

public interface UserService {

	public User saveUser(User user);

	public List<User> getUsers(String keyword);
	
	public User getUser(int id);
	
	public String deleteUser(int id);
	
	public List<Role> getRoles();
	
	public User UpdatUserWithRole(User user);
	
	//public UserDetails loadUserByUsername(String username);
	
}
