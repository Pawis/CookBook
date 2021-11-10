package com.pawis.recipes.MyRecipesWebApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pawis.recipes.MyRecipesWebApp.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
	public Optional<User> findUserByUsername(String user);
	
	@Query("SELECT u FROM User u WHERE u.username LIKE ?1%")
	public List<User> findByKeyword(String keyword);
	
		
}
