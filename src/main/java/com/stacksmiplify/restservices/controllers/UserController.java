package com.stacksmiplify.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksmiplify.restservices.entities.User;
import com.stacksmiplify.restservices.exceptions.UserExistsException;
import com.stacksmiplify.restservices.exceptions.UserNotFoundException;
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
	public ResponseEntity<Void> createUser(@RequestBody User user , UriComponentsBuilder builder) {
		 try{
			  userService.CreateUser(user);
			  HttpHeaders headers = new HttpHeaders();
			  headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			  return new ResponseEntity<Void>(headers , HttpStatus.CREATED);
		 }catch(UserExistsException ex) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ex.getMessage());
		 }
	}
	
	//getUserById 
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try{
			return userService.getUserById(id); 
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND , ex.getMessage());
		}
		
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id , @RequestBody User user) {
		try{
			return userService.updateUserById(id, user);
		}catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST , ex.getMessage());
		}
		
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

