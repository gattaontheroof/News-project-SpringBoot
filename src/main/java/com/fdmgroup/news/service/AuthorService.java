package com.fdmgroup.news.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.Author;
import com.fdmgroup.news.repositories.AuthorRepository;


@Service
public class AuthorService {

	@Autowired
	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}
	
	public List<Author> findAll(){
		return authorRepository.findAll();
	}

	public Optional<Author> findById(int id) {
		return authorRepository.findById(id);
	}

	public List<Author> findByFirstNameAndLastName(String firstName, String lastName) {
		return authorRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	public List<Author> searchAuthors(String searchTerm) {
		return authorRepository.searchAuthors(searchTerm);
	}

	public boolean createAuthor(Author author) {
		Optional<Author> optionalAuthor = authorRepository.findById(author.getId());

		if (!optionalAuthor.isPresent()) {
			System.out.println("Author with ID " + author.getId() + " is being created");
			authorRepository.save(author);
			return true;
		}
		System.out.println("Author with ID " + author.getId() + " already exists");
		return false;
	}

	public Author updateAuthor(Author author) {
		Optional<Author> optionalAuthor = authorRepository.findById(author.getId());

		if (optionalAuthor.isPresent()) {
			System.out.println("Author with ID number " + author.getId() + " is being updated");
			return authorRepository.save(author);
		}
		System.out.println("Author with ID number " + author.getId() + " doesn't exist");
		return null;
	}

}
