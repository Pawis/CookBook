package com.pawis.recipes.MyRecipesWebApp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

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
