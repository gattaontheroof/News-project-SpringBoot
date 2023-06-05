package com.fdmgroup.news.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class News {
	
	@Id
	private int newsId;
	private String title;
	private String content;
	private Author author;
	private LocalDateTime date;
	
	public News() {
		super();
	}

	public News(String title, String content, Author author, LocalDateTime date) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getNewsId() {
		return newsId;
	}
	
	
	

}
