package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.controller.UserController;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	

	@Test
	void showNewUserFormShouldIncludeUserTest() throws Exception {
		
		mockMvc.perform(get("/user/showNewUserForm"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().size(1));
	}
	@Test
	void showNewUserFormRedirectTest() throws Exception {
	
		mockMvc.perform(get("/user/showNewUserForm"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/new-user-form"));
	}
	
	
	@Test
	void processNewUserRedirectTest() throws Exception{
		
		mockMvc.perform(post("/user/processNewUser"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:userList"))
		;
	}
	
	@Test
	void userListRedirectTest() throws Exception{
		
	    mockMvc.perform(get("/user/userList"))
	    .andExpect(status().isOk())
	    .andExpect(view().name("user/user-list"))
	    ;
	}
	/*
	@Test
	void updateUserRedirectTest() throws Exception {

		mockMvc.perform(get("/user/updateUser"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/new-user-form"))
		.andExpect(model().attributeExists("UserId"));
	}
	*/
	
	
	



}
