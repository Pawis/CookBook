package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.controller.UserController;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.security.UserDetailsServiceImpl;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

//TODO: Add more tests for /updateUser

@WebMvcTest(UserController.class )
@Import(CustomUserDetailService.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;
	@MockBean
	private UserDetailsServiceImpl impl;
	
	@Test
	@WithMockUser
	void getUpdateUserShouldReturnViewWithPrefilledData() throws Exception {
		User user = new User();
		ArrayList<Role> role= new ArrayList<>();
		
		when(userService.getUser(1)).thenReturn(user);
		when(userService.getRoles()).thenReturn(role);
		
		this.mockMvc
		.perform(get("/user/updateUser").param("userId", "1"))
		.andExpect(status().isOk())
        .andExpect(view().name("user/update-user-form"))
        .andExpect(model().attribute("user", user))
		.andExpect(model().attribute("roles", role));
	}
	
	@Test
	@WithUserDetails(value = "admin", userDetailsServiceBeanName="userDetailsServiceAdmin")
	void postUpdateValidationTest() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("123");
		user.setLastName("123");
		user.setUsername("username");
		user.setPassword("password");
		Role roles = new Role(3, "ADMIN");
		user.addRole(roles);
		
		mockMvc.perform(post("/user/updateUser").with(csrf()).flashAttr("user", user))
		.andExpect(status().isOk())
		.andExpect(view().name("user/update-user-form"));
		
	}
	
	@Test
	@WithUserDetails(value = "admin", userDetailsServiceBeanName="userDetailsServiceAdmin")
	void postUpdateSucces() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("firsttest");
		user.setLastName("lasttest");
		user.setUsername("username");
		user.setPassword("password");
		Role roles = new Role(3, "ADMIN");
		user.addRole(roles);
		
		mockMvc.perform(post("/user/updateUser").with(csrf()).flashAttr("user", user))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:userList"));
	}
	
	@Test
	@WithMockUser
	void getUserList() throws Exception {
		
		ArrayList<User> users = new ArrayList<>();
		User user = new User();
		users.add(user);
		
		when(userService.getUsers("keyword")).thenReturn(users);
		
		mockMvc.perform(get("/user/userList").param("keyword", "keyword"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/user-list"))
		.andExpect(model().attribute("users", users));
		
	}
	
	@Test
	@WithMockUser
	void getDeleteUser() throws Exception {
		
		when(userService.deleteUser(1)).thenReturn("SUCCES");
		
		mockMvc.perform(get("/user/deleteUser").param("userId", "1"))
		.andExpect(status().is3xxRedirection());
		
	}
		
		

}
