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


import com.fdmgroup.news.model.Author;
import com.fdmgroup.news.model.News;
import com.fdmgroup.news.service.NewsService;







@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping
	public List<News> findAllNews() {
		return newsService.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<News>> getNewsId(@PathVariable int newsId) {
		return ResponseEntity.status(HttpStatus.OK).body(newsService.findById(newsId));
	}

	@PostMapping
	public ResponseEntity<News> createNews(@RequestBody News news) {
		if (newsService.createNews(news)) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{newsId}")
					.buildAndExpand(news.getNewsId()).toUri();
			return ResponseEntity.created(location).build();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PutMapping
	public ResponseEntity<News> updateNews(@RequestBody News news) {
		News updatedNews = newsService.updateNews(news);

		if (updatedNews != null) {
			return ResponseEntity.ok(newsService.updateNews(news));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{newsId}")
	public ResponseEntity<Void> deleteNews(@PathVariable int newsId) {
		System.out.println("Deleting " + newsId);
		newsService.deleteById(newsId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<News> getNewsByTitle(@PathVariable("title") String title) {
		News news = newsService.getNewsByTitle(title);

		if (news != null) {
			return ResponseEntity.status(HttpStatus.OK).body(news);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}


	@GetMapping("/author/{id}")
	public ResponseEntity<News> getAllNewsByAuthor1(@RequestBody Author author)
	{return ResponseEntity.ok(newsService.getNewsByAuthor(author));
	}
	
	

	
	@GetMapping("/author/{id}")
	public ResponseEntity<News> getAllNewsByAuthor(@RequestBody Author author){	
		return ResponseEntity.ok(newsService.getNewsByAuthor(author));
	}
	
	
	
	
	@GetMapping("searchNewsByAuthor")
	public ResponseEntity<List<News>> searchNews(@PathVariable String searchTerm){
		return ResponseEntity.ok(newsService.searchNews(searchTerm));
	}


	
	
	
	

}

