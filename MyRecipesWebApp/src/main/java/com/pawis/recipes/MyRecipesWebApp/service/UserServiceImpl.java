package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawis.recipes.MyRecipesWebApp.dao.RoleRepository;
import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role roleSuer = roleRepo.findByName("USER");
		user.addRole(roleSuer);
		userRepo.save(user);
		return user;
	}
	@Override
	@Transactional
	public User UpdatUserWithRole(User user) {
		
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
	public List<Role> getRoles() {
		List<Role> roles = roleRepo.findAll();
		return roles;
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
	@Transactional
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}
	

}
