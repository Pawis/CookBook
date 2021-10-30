package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;

import com.pawis.recipes.MyRecipesWebApp.entity.User;

public interface UserService {

	public User saveUser(User user);

	public List<User> getUsers();
	
	public User getUser(int id);
	
	public void deleteUser(int id);

}
