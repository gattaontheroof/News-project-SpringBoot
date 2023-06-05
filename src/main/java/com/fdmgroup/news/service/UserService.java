package com.fdmgroup.news.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.User;
import com.fdmgroup.news.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}
	
	public User getUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		} 
		return null;
	}
	
	public boolean createUser(User user){
		Optional<User> optionalUser = userRepository.findById(user.getId());
		
		if (! optionalUser.isPresent()) {
			System.out.println("User with ID " + user.getId()+" is being created");
			userRepository.save(user);
			return true;
		}
		System.out.println("User with ID " + user.getId()+" already exists");
		return false;
	}
	
	public User updateUser(User user) {
		Optional<User> optionalUser = userRepository.findById(user.getId());
		
		if (optionalUser.isPresent()) {
			System.out.println("ID number " + user.getId()+" is being updated");
			return userRepository.save(user);
		}
		System.out.println("ID number " + user.getId()+" doesn't exist");
		return null;
	}
	
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}
	
	

}
