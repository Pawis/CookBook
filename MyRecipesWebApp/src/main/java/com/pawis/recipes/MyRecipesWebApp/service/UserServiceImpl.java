package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userRepo;

	@Override
	@Transactional
	public User saveUser(User user) {
		userRepo.save(user);
		return user;
	}

	@Override
	@Transactional
	public List<User> getUsers() {

		List<User> users = userRepo.findAll();

		return users;
	}

	@Override
	@Transactional
	public User getUser(int id) {

		User user = null;
		Optional<User> userr = userRepo.findById(id);

		if (userr.isPresent())
			user = userr.get();

		return user;
	}

	@Override
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

}
