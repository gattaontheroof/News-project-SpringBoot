package com.fdmgroup.news.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.fdmgroup.news.model.News;


import com.fdmgroup.news.model.Author;



public interface NewsRepository extends JpaRepository<News, Integer>{

	public Optional<News> findByTitle(String title);
	
	public Optional<News> findByAuthor(Author author);
	
	//search by title or content
	@Query ("select a from Author a " + "where lower(a.title) like lower(concat('%', :searchTerm, '%')) or" +
			"lower(a.content) like lower(concat('%', :searchTerm, '%'))")
	public List<News> searchNews(String searchTerm);
}
