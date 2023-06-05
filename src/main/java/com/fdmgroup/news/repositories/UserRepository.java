package com.fdmgroup.news.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.news.model.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);

	Optional<User> findById(int id);

	

}
