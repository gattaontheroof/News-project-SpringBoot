package com.fdmgroup.news.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.news.model.Author;
import com.fdmgroup.news.model.News;
import com.fdmgroup.news.repositories.NewsRepository;


@Service
public class NewsService {

	@Autowired
	private final NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		super();
		this.newsRepository = newsRepository;
	}

	public List<News> findAll() {
		return newsRepository.findAll();
	}

	public Optional<News> findById(int id) {
		return newsRepository.findById(id);
	}

	public List<News> searchNews(String searchTerm) {
		return newsRepository.searchNews(searchTerm);
	}

	public News getNewsByTitle(String title) {
		Optional<News> optionalNews = newsRepository.findByTitle(title);
		if (optionalNews.isPresent()) {
			return optionalNews.get();
		}
		return null;
	}

	public News getNewsByAuthor(Author author) {
		Optional<News> optionalNews = newsRepository.findByAuthor(author);
		if (optionalNews.isPresent()) {
			return optionalNews.get();
		}
		return null;
	}

	public boolean createNews(News news) {
		Optional<News> optionalNews = newsRepository.findById(news.getNewsId());

		if (!optionalNews.isPresent()) {
			System.out.println("News article with ID " + news.getNewsId() + " is being created");
			newsRepository.save(news);
			return true;
		}
		System.out.println("News article with ID " + news.getNewsId() + " already exists");
		return false;
	}

	public News updateNews(News news) {
		Optional<News> optionalNews = newsRepository.findById(news.getNewsId());

		if (optionalNews.isPresent()) {
			System.out.println("news article with ID number " + news.getNewsId() + " is being updated");
			return newsRepository.save(news);
		}
		System.out.println(" news article with ID number " + news.getNewsId() + " doesn't exist");
		return null;
	}

	public void deleteById(int id) {
		newsRepository.deleteById(id);
	}

}