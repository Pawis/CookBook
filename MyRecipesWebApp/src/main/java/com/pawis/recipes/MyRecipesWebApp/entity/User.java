package com.pawis.recipes.MyRecipesWebApp.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "Cannot be empty")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "Cannot be empty")
	@Column(name = "last_name")
	private String lastName;

	@Size(min = 3, max = 10, message = "Must be longer than 3 and shorter than 10")
	@Column(name = "username")
	private String username;

	@Size(min = 4, message = "Must be longer than 4 ")
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Role> roles;

	public User() {

	}

	public User(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * public List<Recipe> getRecipes() { return recipes; }
	 * 
	 * public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void addRole(Role role) {
		if (roles == null)
			roles = new HashSet<>();
		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
	}
	/*
	 * public void add(Recipe recipe) {
	 * 
	 * if (recipes == null) recipes = new ArrayList<Recipe>();
	 * 
	 * recipes.add(recipe);
	 * 
	 * recipe.setAuthor(this); }
	 */

}
