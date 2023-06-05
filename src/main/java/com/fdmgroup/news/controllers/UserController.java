package com.fdmgroup.news.controllers;

import java.net.URI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.service.UserService;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> findAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<User>> getUserId(@PathVariable int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
	}

	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByUsername(username));
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (userService.createUser(user)) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
					.buildAndExpand(user.getUsername()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User updatedUser = userService.updateUser(user);

		if (updatedUser != null) {
			return ResponseEntity.ok(userService.updateUser(user));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
		System.out.println("Deleting " + userId);
		userService.deleteById(userId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
