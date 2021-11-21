package com.pawis.recipes.MyRecipesWebApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.expections.ErrorResponse;
import com.pawis.recipes.MyRecipesWebApp.expections.UserAlredyExistsException;
import com.pawis.recipes.MyRecipesWebApp.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String mainMenu(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated())
		return "redirect:user/userList";
		return "/Login";
	}

	@GetMapping("/gitHubOauth")
	private String gitHubOatuh() {
		return "github-oauth2";
	}

	@GetMapping("/Login")
	public String loginForm() {
		return "/Login";
	}

	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "/user/register-new-user";
	}

	@PostMapping("/processRegister")
	public String processRegisterForm(@Valid User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			return "/user/register-new-user";
		}
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			bindingResult.rejectValue("username", "user.username", " An account alredy exists for this username");
			user.setPassword(null);
			model.addAttribute("registrationForm", user);
			return "/user/register-new-user";
			// throw new UserAlredyExistsException("Username alredy exists = " +
			// user.getUsername());
		}

		return "redirect:user/userList";

	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(UserAlredyExistsException exc) {

		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.I_AM_A_TEAPOT);
	}
}
