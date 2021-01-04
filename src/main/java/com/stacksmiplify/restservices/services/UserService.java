package com.stacksmiplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksmiplify.restservices.entities.User;
import com.stacksmiplify.restservices.repositories.UserRepository;

//Service
@Service
public class UserService {
	
	//Autowire the Repository
	
	@Autowired
	private UserRepository userRepository;
	
	//getAllUsers Method
	public List<User> getAllUsers(){
		
		return userRepository.findAll();
		
	}
	
	//createUser Method
	
	public User CreateUser(User user) {
		return userRepository.save(user);
	}
	
	//getUserByID
	public Optional<User> getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		return user;
	}
	
	
	//updateUserById
	public User updateUserById( Long id , User user) {
		user.setId(id);
		return userRepository.save(user);
		 
	}
	
	//deleteUserById
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}
	
	//getUserByUserrname
	public User getUserByUSername(String username) {
		return userRepository.findByUsername(username);
	}

}
