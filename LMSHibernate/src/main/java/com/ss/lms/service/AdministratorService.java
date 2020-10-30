package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;
import com.ss.lms.entity.Genre;
import com.ss.lms.entity.Loans;
import com.ss.lms.entity.Publisher;
import com.ss.lms.repo.AuthorRepo;
import com.ss.lms.repo.BookRepo;
import com.ss.lms.repo.GenreRepo;
import com.ss.lms.repo.PublisherRepo;

@RestController
public class AdministratorService {

	
	@Autowired
	BookRepo brepo;
	
	@Autowired
	AuthorRepo arepo;
	
	@Autowired
	GenreRepo grepo;

	@Autowired
	PublisherRepo prepo;

	@Transactional
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> addBook(@RequestBody Book book) throws SQLException {
		
//			if (book.getTitle() != null && book.getTitle().length() > 45) {
//				return "Book Title cannot be empty and should be 45 char in length";
//			}
			
			//book.setBookId(bdao.addBookWithPk(book));
//			if(!book.getAuthors().isEmpty())
//			for (Author a : book.getAuthors()) {
//				adao.addBookAuthors(book.getBookId(), a.getAuthorId());
//			
//			}
		List<Author> authors = new ArrayList<>();
		if(!book.getAuthors().isEmpty())
		for (Author a : book.getAuthors()) {
			List<Author> tempAuthors = new ArrayList<>();
			int authorId = a.getAuthorId();
			tempAuthors= arepo.readAuthorsById(authorId);
			if(!tempAuthors.isEmpty())
				authors.add(tempAuthors.get(0));
		}
		book.setAuthors(authors);
		
		
		
		List<Genre> genres = new ArrayList<>();
		if(!book.getGenres().isEmpty())
		for (Genre g : book.getGenres()) {
			List<Genre> tempGenres = new ArrayList<>();
			int genreId = g.getGenreId();
			tempGenres= grepo.readGenresById(genreId);
			if(!tempGenres.isEmpty())
				genres.add(tempGenres.get(0));
		}
		book.setGenres(genres);
		
		
		
//		if(book.getPublisher()!= null) {
//			
//		}
			
			///if(book.getPublisher()!= null)
				////do i even need to add publisher?
			
			//return getBooks(null);
			brepo.save(book);
			return getAllBooks();
		
	}
//
//	@RequestMapping(value = "/getBooks/{searchString}", method = RequestMethod.GET, produces = "application/json")
//	public List<Book> getBooks(@PathVariable String searchString) {
//		try {
//			if (searchString != null) {
//				return bdao.readAllBooksByName(searchString);
//			} else {
//				return bdao.readAllBooks();
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
	@RequestMapping(value = "/getBooksByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBooksByQuery(@RequestParam String searchString) {
		List<Book> books = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				books = brepo.readBooksByTitle(searchString);
		} else {
				books = brepo.findAll();
		}
		return books;
	}
	
	
	@RequestMapping(value = "/getAllBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		books = brepo.findAll();
		return books;
	}
	
	@RequestMapping(value = "/getAllGenres", method = RequestMethod.GET, produces = "application/json")
	public List<Genre> getAllGenres() {
		List<Genre> genres = new ArrayList<>();
		genres = grepo.findAll();
		return genres;
	}
	
	@RequestMapping(value = "/getAllAuthors", method = RequestMethod.GET, produces = "application/json")
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<>();
		authors = arepo.findAll();
		return authors;
	}
	
	@RequestMapping(value = "/getAllPublishers", method = RequestMethod.GET, produces = "application/json")
	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers = new ArrayList<>();
		publishers = prepo.findAll();
		return publishers;
	}
	
	
	@Transactional
	@RequestMapping(value = "/addGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> addGenre(@RequestBody Genre genre) throws SQLException { 
		
				grepo.save(genre);
				// genre.setGenreId(grepo.save(entity));
				 //conn.commit();
				 System.out.println("Addition successful");
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Genre> deleteGenre(@RequestBody Genre genre) throws SQLException { 
		
				grepo.delete(genre);
				// genre.setGenreId(grepo.save(entity));
				 //conn.commit();
				 //System.out.println("removal successful");
				 return(getAllGenres());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteBook", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> deleteBook(@RequestBody Book book) throws SQLException { 
		
				brepo.delete(book);
				// book.setBookId(grepo.save(entity));
				 //conn.commit();
				 //System.out.println("removal successful");
				 return(getAllBooks());
		
	}
	
	@Transactional
	@RequestMapping(value = "/deleteBookById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<Book> deleteBookById(@RequestBody Book book) throws SQLException { 
		
				if(book.getBookId()== null)
					return getAllBooks();
				
				Book b = getBookById(book.getBookId());
				brepo.delete(b);
				// book.setBookId(grepo.save(entity));
				 //conn.commit();
				 //System.out.println("removal successful");
				 return(getAllBooks());
		
	}
	
	public Book getBookById(int bookId) throws SQLException{
		List<Book> books = brepo.readBooksById(bookId);
		if(!books.isEmpty())
			return books.get(0);
		return null;
		
	}
	
	
	public Publisher getPublisherById(int publisherId) throws SQLException{
		List<Publisher> publishers = prepo.readPublishersById(publisherId);
		if(!publishers.isEmpty())
			return publishers.get(0);
		return null;
		
	}
	
	public Genre getGenreById(int genreId) throws SQLException{
		List<Genre> genres = grepo.readGenresById(genreId);
		if(!genres.isEmpty())
			return genres.get(0);
		return null;
		
	}
	
	public Author getAuthorById(int authorId) throws SQLException{
		List<Author> authors = arepo.readAuthorsById(authorId);
		if(!authors.isEmpty())
			return authors.get(0);
		return null;
		
	}
	
	
	
	
//	
//	
//	@RequestMapping(value = "/getAllAuthors", method = RequestMethod.GET, produces = "application/json")
//	public List<Author> getAllAuthors() {
//		try {
//		
//				return adao.readAllAuthors();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value = "/getAllGenres", method = RequestMethod.GET, produces = "application/json")
//	public List<Genre> getAllGenres() {
//		try {
//		
//				return gdao.readAllGenres();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value = "/getAllLoans", method = RequestMethod.GET, produces = "application/json")
//	public List<Loans> getAllLoans() {
//		try {
//		
//				return ldao.readAllLoans();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	@RequestMapping(value = "/getAllPublishers", method = RequestMethod.GET, produces = "application/json")
//	public List<Publisher> getAllPublishers() {
//		try {
//		
//				return pdao.readAllPublishers();
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	


}