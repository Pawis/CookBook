package com.pawis.recipes.MyRecipesWebApp.config;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LoginForm {
	
	@NotNull(message="is required")
	@Size(min=3)
	private String userName;
	
	@NotNull(message="is required")
	private String password;

	
	public LoginForm() {
	}

	public LoginForm(@NotNull(message = "is required") @Size(min = 3) String userName,
			@NotNull(message = "is required") String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
