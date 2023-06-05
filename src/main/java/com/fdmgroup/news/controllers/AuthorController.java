package com.fdmgroup.news.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.news.model.Author;
import com.fdmgroup.news.service.AuthorService;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	
	@GetMapping
	public List<Author> findAll(){
		return authorService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Author>> getNewsId(@PathVariable int Id){	
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(authorService.findById(Id));
	}
	
	@GetMapping("searchAuthorsByName")
	public ResponseEntity<List<Author>>  findByFirstNameAndLastName( @PathVariable String lastName,
	 @PathVariable String firstName) {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(authorService.findByFirstNameAndLastName(firstName, lastName));
				
	    }
	

}
