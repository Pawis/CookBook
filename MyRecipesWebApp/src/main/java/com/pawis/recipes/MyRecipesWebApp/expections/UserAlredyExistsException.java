package com.pawis.recipes.MyRecipesWebApp.expections;

@SuppressWarnings("serial")
public class UserAlredyExistsException extends RuntimeException{

	public UserAlredyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAlredyExistsException(String message) {
		super(message);
	}

	public UserAlredyExistsException(Throwable cause) {
		super(cause);
	}
	
	
}
