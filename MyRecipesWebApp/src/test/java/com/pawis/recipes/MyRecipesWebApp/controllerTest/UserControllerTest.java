package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.controller.UserController;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;


@WebMvcTest(UserController.class )
//@Import(SecurityConfig.class)
@ContextConfiguration(classes = {UserServiceImpl.class })
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserServiceImpl userService;
	
	
	@Test
	//@WithUserDetails(value="Pawis")
	@WithMockUser(authorities ="ADMIN")
	void shouldReturnViewWithPrefilledData() throws Exception {
		/*
		User user = new User();
		user.setFirstName("testFirst");
		user.setLastName("testLast");
		user.setPassword("test");
		user.setUsername("testUsername");
		user.setId(1);
		Role roleUSER = new Role(1,"USER");
		Set<Role> roles = new HashSet<>();
		roles.add(roleUSER);
		user.setRoles(roles);
		*/
		
		when(userService.getUser(1)).thenReturn(new User());
		
		this.mockMvc
		.perform(get("/user/updateUser?userId=124"))
		.andExpect(status().isOk())
        .andExpect(view().name("update-user-form"))
        .andExpect(model().attribute("user", any(User.class)))
		.andExpect(model().attribute("roles", new Role()));
	}




}
