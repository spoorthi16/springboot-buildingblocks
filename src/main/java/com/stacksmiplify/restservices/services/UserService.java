package com.stacksmiplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksmiplify.restservices.entities.User;
import com.stacksmiplify.restservices.exceptions.UserExistsException;
import com.stacksmiplify.restservices.exceptions.UserNotFoundException;
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
	
	public User CreateUser(User user) throws UserExistsException {
		//if user exists using username
		User existingUser = userRepository.findByUsername(user.getUsername());
		
		if(existingUser != null) {
			throw new UserExistsException("User already exists in repository");
		}
		//if not throw UserExistsException
		return userRepository.save(user);
	}
	
	//getUserByID
	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in user Repository");
		}
		return user;
	}
	
	
	//updateUserById
	public User updateUserById( Long id , User user) throws UserNotFoundException {
		
			Optional<User> optionaluser = userRepository.findById(id);
		
		if(!optionaluser.isPresent()) {
			throw new UserNotFoundException("User not found in the user Repository, provide the correct user ID");
		}
		
		user.setId(id);
		return userRepository.save(user);
		 
	}
	
	//deleteUserById
	public void deleteUserById(Long id)  {
Optional<User> optionaluser = userRepository.findById(id);
		
		if(!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "User not found in the user Repository, provide the correct user ID");
		}
		userRepository.deleteById(id);
	}
	
	//getUserByUserrname
	public User getUserByUSername(String username) {
		return userRepository.findByUsername(username);
	}

}
