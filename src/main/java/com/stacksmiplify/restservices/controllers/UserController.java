package com.stacksmiplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksmiplify.restservices.entities.User;
import com.stacksmiplify.restservices.services.UserService;

//Controller
@RestController
public class UserController {

	//Autowire the userService
	@Autowired
	private UserService userService ;
	
	//getAllUsers Method
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		return userService.getAllUsers();
	}
	
	//create User method -
	//@RequestBody annotation
	//@PostMapping Annotation
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		 return userService.CreateUser(user);
	}
	
	//getUserById 
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id); 
		
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id , @RequestBody User user) {
		return userService.updateUserById(id, user);
		
	}
	
	//deleteUserByID
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//getUserByUsernmae
	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUSername(username);
	}
}

