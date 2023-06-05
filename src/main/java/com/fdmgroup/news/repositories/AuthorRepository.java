package com.fdmgroup.news.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fdmgroup.news.model.Author;


public interface AuthorRepository extends JpaRepository<Author, Integer>{
	
	public List<Author> findByFirstNameAndLastName (String firstName, String lastName);
	
	@Query ("select a from Author a " + "where lower(a.firstName) like lower(concat('%', :searchTerm, '%')) or" +
	"lower(a.lastName) like lower(concat('%', :searchTerm, '%'))")
	public List<Author> searchAuthors(String searchTerm);

}
