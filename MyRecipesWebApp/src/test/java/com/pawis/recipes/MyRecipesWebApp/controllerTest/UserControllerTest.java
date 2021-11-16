package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.config.SecurityConfig;
import com.pawis.recipes.MyRecipesWebApp.controller.UserController;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;


@WebMvcTest(UserController.class )
//@Import(SecurityConfig.class)
@Import(UserController.class)
@ContextConfiguration(classes = SecurityConfig.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;
	
	
	@Test
	//@WithUserDetails(userDetailsServiceBeanName="UserService")
	@WithMockUser
	void shouldReturnViewWithPrefilledData() throws Exception {
		
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




}
