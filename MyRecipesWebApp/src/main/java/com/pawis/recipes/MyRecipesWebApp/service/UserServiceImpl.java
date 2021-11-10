package com.pawis.recipes.MyRecipesWebApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pawis.recipes.MyRecipesWebApp.dao.RoleRepository;
import com.pawis.recipes.MyRecipesWebApp.dao.UsersRepository;
import com.pawis.recipes.MyRecipesWebApp.entity.AppUserDetails;
import com.pawis.recipes.MyRecipesWebApp.entity.Role;
import com.pawis.recipes.MyRecipesWebApp.entity.User;
import com.pawis.recipes.MyRecipesWebApp.entity.UserDTO;
import com.pawis.recipes.MyRecipesWebApp.expections.UserNotFoundException;

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
		Role userRole = roleRepo.findByName("USER");
		user.addRole(userRole);
		userRepo.save(user);

		return user;
	}

	@Override
	@Transactional
	public User UpdatUserWithRole(User user) {

		userRepo.save(user);
		return user;
	}
/*
	@Override
	@Transactional
	public List<User> searchUsersBy(String findBy) {

		List<User> users = userRepo.findByKeyword(findBy);
		return users;
	}
	*/

	@Override
	@Transactional
	public List<User> getUsers(String keyword) {

		if (keyword != null) {
			List<User> users = userRepo.findByKeyword(keyword);
			return users;
		}
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
		else {
			throw new UserNotFoundException("User id not found - " + id);
		}

		return user;
	}
	
	public List<UserDTO> getUserDTOs() {
		return getUsers(null)
				.stream()
				.map(this::convertUsertoUserDTO)
				.collect(Collectors.toList());
	}
	
	public UserDTO getSingleUserDTO(int id) {
		return convertUsertoUserDTO(getUser(id));
		
	}
	
	
	private UserDTO convertUsertoUserDTO(User user) {
		
		UserDTO userDto = new UserDTO();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());		
		userDto.setLastName(user.getLastName());
		return userDto;
			
		
	}

	@Override
	@Transactional
	public void deleteUser(int id) {
		AppUserDetails auth = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (auth.getId() != id)
			userRepo.deleteById(id);
	}

}
