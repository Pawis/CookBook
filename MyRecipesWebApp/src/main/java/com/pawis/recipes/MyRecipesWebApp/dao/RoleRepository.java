package com.pawis.recipes.MyRecipesWebApp.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pawis.recipes.MyRecipesWebApp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String name);
	
}
