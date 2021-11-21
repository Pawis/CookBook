package com.pawis.recipes.MyRecipesWebApp.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pawis.recipes.MyRecipesWebApp.dao.RoleRepository;
import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	
	@InjectMocks
	private UserServiceImpl userService;
	@Mock
	private UsersRepository userRepo;
	@Mock
	private RoleRepository roleRepo;
	@Mock
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	void saveUserTest() {
		User user = new User();
		user.setUsername("TestUsername");
		user.setFirstName("FirstName");
		user.setLastName("LastName");
		user.setId(1);
		user.setPassword("password");
		Role role = new Role(1,"USER");
		
		when(userRepo.save(any(User.class))).thenReturn(user);
		when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");
		when(roleRepo.findByName(anyString())).thenReturn(role);
		
		User savedUser = userService.saveUser(user);
		assertTrue(savedUser.getPassword().equals("hashedPassword"));
		assertTrue(savedUser.getRoles().contains(role));
		assertNotNull(savedUser.getUsername());
	}
}
