package com.pawis.recipes.MyRecipesWebApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pawis.recipes.MyRecipesWebApp.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

}
