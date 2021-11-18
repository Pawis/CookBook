package com.pawis.recipes.MyRecipesWebApp.controllerTest;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pawis.recipes.MyRecipesWebApp.entity.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

@TestConfiguration
public class CustomUserDetailService {
	
	@Bean("userDetailsService")
	@Primary
	public UserDetailsService userDetailsService() {

		class UserWithDetails implements UserDetailsService {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				User user = new User();
				user.setId(2);
				user.setFirstName("admin1");
				user.setLastName("admin2");
				user.setUsername("admin");
				user.setPassword("admin");
				Role roles = new Role(1, "ADMIN");
				user.addRole(roles);
				AppUserDetails userDetails = new AppUserDetails(user);
				return userDetails;
			}
			
		}
		return new UserWithDetails();
		
	
	}

}
