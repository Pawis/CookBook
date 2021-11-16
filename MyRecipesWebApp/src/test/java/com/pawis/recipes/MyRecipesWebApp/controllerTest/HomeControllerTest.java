package com.pawis.recipes.MyRecipesWebApp.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.pawis.recipes.MyRecipesWebApp.config.SecurityConfig;
import com.pawis.recipes.MyRecipesWebApp.controller.HomeController;
import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;
import com.pawis.recipes.MyRecipesWebApp.service.UserServiceImpl;

@WebMvcTest(HomeController.class )
@ContextConfiguration(classes = SecurityConfig.class)
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	
	@Test
	void testMainPage() throws Exception{
		mockMvc.perform(get("/"))
		.andExpect(status().isOk());
	}

}
