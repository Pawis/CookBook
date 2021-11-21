package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.controller.HomeController;
import com.pawis.recipes.MyRecipesWebApp.security.UserDetailsServiceImpl;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

@WebMvcTest(HomeController.class )
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	@MockBean
	private UserDetailsServiceImpl impl;
	
	/*
	@Test
	@WithAnonymousUser
	void testMainPage() throws Exception{
		
		mockMvc.perform(get("/"))
		.andExpect(view().name("/Login"))
		.andExpect(status().isOk());
	}
	*/
	
	@Test
	@WithMockUser
	void testMainPageWithLoggedInUser() throws Exception{
		
		mockMvc.perform(get("/"))
		.andExpect(view().name("redirect:user/userList"))
		.andExpect(status().is3xxRedirection());
	}
	
	@Test
	void testLoginPage() throws Exception{
		mockMvc.perform(get("/Login"))
		.andExpect(view().name("/Login"))
		.andExpect(status().isOk());
	}
	
	@Test
	void testRegisterPage() throws Exception{
		
		mockMvc.perform(get("/register"))
		.andExpect(model().attributeExists("user"))
		.andExpect(model().size(1))
		.andExpect(view().name("/user/register-new-user"))
		.andExpect(status().isOk());
	}
	
	

}
