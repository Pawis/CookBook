package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

// @Service
public class UserServiceImpl1 implements UserDetailsService {

	@Autowired
	private UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepo.findUserByUsername(username);

		if (user.isPresent()) {
			return new AppUserDetails(user.get());
		} else {
			throw new UsernameNotFoundException("User Not Found");
		}

	}

}
