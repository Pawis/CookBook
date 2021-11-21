package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.config.SecurityConfig;
import com.pawis.recipes.MyRecipesWebApp.controller.UserController;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.security.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.security.UserDetailsServiceImpl;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal().toString());
		
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
	@WithUserDetails(value = "admin", userDetailsServiceBeanName="userDetailsService")
	void postUpdateUserCantModifyOwnFields() throws Exception {
		
		User user = new User();
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal().toString());
		mockMvc.perform(post("/user/updateUser").flashAttr("user", user));

		
	}
	




}
