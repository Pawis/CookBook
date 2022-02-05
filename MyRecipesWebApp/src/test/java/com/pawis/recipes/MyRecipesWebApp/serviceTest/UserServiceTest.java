package com.pawis.recipes.MyRecipesWebApp.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import com.pawis.recipes.MyRecipesWebApp.entity.UserDTO;
import com.pawis.recipes.MyRecipesWebApp.expections.UserNotFoundException;
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
	
	@Test
	void updateUserWithRoleTest() {
		User user = new User();
		when(userRepo.save(any())).thenReturn(user);
		
		User savedUser = userService.UpdatUserWithRole(user);
		assertNotNull(savedUser);
	}
	
	@Test
	void getUsersTest() {
		User user1 = new User();
		User user2 = new User();
		ArrayList<User> users = new ArrayList<>();
		users.add(user2);
		users.add(user1);
		when(userRepo.findAll()).thenReturn(users);
		assertTrue(userService.getUsers(null).contains(user1));
		
	}
	
	@Test
	void getUsersWithKeyowrdTest() {
		User user1 = new User();
		User user2 = new User();
		ArrayList<User> users = new ArrayList<>();
		users.add(user2);
		users.add(user1);
		when(userRepo.findByKeyword("a")).thenReturn(users);
		assertTrue(userService.getUsers("a").contains(user1));
		
	}
	@Test
	void getRolesTest() {
		
		Role role  = new Role(1,"USER");
		ArrayList<Role> roles = new ArrayList<>();
		roles.add(role);
		when(roleRepo.findAll()).thenReturn(roles);
		assertTrue(userService.getRoles().contains(role));
		
	}
	
	@Test
	void getUserTest() {
		
		User user = new User();
		Optional<User> userOptional = Optional.ofNullable(user);
		
		when(userRepo.findById(1)).thenReturn(userOptional);
		Optional<User> foundUser = userRepo.findById(1);
		
		assertNotNull(userService.getUser(1));
		
	}
	
	@Test
	void getUserNotFoundTest() {
		
		User user = null;
		Optional<User> userOptional = Optional.ofNullable(user);
		when(userRepo.findById(1)).thenReturn(userOptional);
		Optional<User> foundUser = userRepo.findById(1);

		UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
			userService.getUser(1);
		}, "UserNotFoundException was excepted" );
		
		assertEquals("User id not found - 1", thrown.getMessage());
	}
	
	
	@Test
	void getUsersDTOsTest() {
		User user = new User();
		user.setFirstName("testFirst");
		user.setLastName("testLast");
		user.setId(1);
		ArrayList<User> users = new ArrayList<>();
		users.add(user);
		UserDTO userDto = new UserDTO(user);
		
		ArrayList<UserDTO> usersDTO = new ArrayList<>();
		
		when(userService.getUsers(null)).thenReturn(users);
		when(userService.getUserDTOs()).thenReturn(usersDTO);
		
		assertNotNull(userService.getUserDTOs());
		
	}
	
	//TODO:
	/*
	@Test
	void getUserDTOTest() {
		
		User user = new User();
		user.setId(1);
		Optional<User> userOptional = Optional.ofNullable(user);
		
		UserDTO userDto = new UserDTO(user);
		
		when(userRepo.findById(1)).thenReturn(userOptional);
		when(userService.getUser(1)).thenReturn(user);
		when(userService.getSingleUserDTO(1)).thenReturn(userDto);
		
		assertEquals(userService.getSingleUserDTO(1),userDto);
	}
	*/
	
	
}
