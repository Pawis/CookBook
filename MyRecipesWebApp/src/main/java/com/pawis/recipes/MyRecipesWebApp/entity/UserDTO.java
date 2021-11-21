package com.pawis.recipes.MyRecipesWebApp.entity;

public class UserDTO {

	private int Id;

	private String firstName;

	private String lastName;

	public UserDTO(User user) {
		this.Id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
