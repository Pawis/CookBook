package com.pawis.recipes.MyRecipesWebApp.controllerTest;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.security.AppUserDetails;

@TestConfiguration
public class CustomUserDetailService {
	
	@Bean("userDetailsServiceAdmin")
	public UserDetailsService userDetailsServiceAdmin() {

		class UserWithDetails implements UserDetailsService {

			@Override
			public UserDetails loadUserByUsername(String username)  {
				User user = new User();
				user.setId(2);
				user.setFirstName("adminFirst");
				user.setLastName("adminLast");
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
	
	@Bean("userDetailsServiceUser")
	public UserDetailsService userDetailsServiceUser() {

		class UserWithDetails implements UserDetailsService {

			@Override
			public UserDetails loadUserByUsername(String username)  {
				User user = new User();
				user.setId(2);
				user.setFirstName("userFirst");
				user.setLastName("userLast");
				user.setUsername("userName");
				user.setPassword("userPass");
				Role roles = new Role(1, "USER");
				user.addRole(roles);
				AppUserDetails userDetails = new AppUserDetails(user);
				return userDetails;
			}
			
		}
		return new UserWithDetails();
	
	}
}
