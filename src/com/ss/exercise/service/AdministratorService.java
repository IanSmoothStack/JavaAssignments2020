/**
 * 
 */
package com.ss.exercise.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.exercise.dao.AuthorDAO;
import com.ss.exercise.dao.BookDAO;
import com.ss.exercise.dao.BranchDAO;
import com.ss.exercise.dao.GenreDAO;
import com.ss.exercise.dao.PublisherDAO;
import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Genre;
import com.ss.exercise.entity.Publisher;

/**
 * @author Ian
 *
 */
public class AdministratorService {
	public ConnectionUtil conUtil = new ConnectionUtil();

	public String addBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			GenreDAO gdao = new GenreDAO(conn);
			PublisherDAO pdao = new PublisherDAO(conn);
			if (book.getTitle() != null && book.getTitle().length() > 45) {
				return "Book Title cannot be empty and should be 45 char in length";
			}
			book.setBookId(bdao.addBookWithPk(book));
//			book.setBookId(bdao.addBookWithPk2(book));
			if(!book.getAuthors().isEmpty())
			for (Author a : book.getAuthors()) {
				adao.addBookAuthors(book.getBookId(), a.getAuthorId());
		
			}
			
			if(!book.getGenres().isEmpty())
				for( Genre g: book.getGenres()) {
					gdao.addBookGenres(book.getBookId(), g.getGenreId());
				}
			
			if(book.getPublisher()!=null)
				pdao.addBookPublishers(book.getBookId(), book.getPubId());
			// Do the same for genres/branches etc.
			// for(Author a: book.getAuthors()) {
			// adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			// }
			conn.commit();
			return "Book added sucessfully";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			return "Unable to add book - contact admin.";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
	public String addBook2(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = conUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			AuthorDAO adao = new AuthorDAO(conn);
			if (book.getTitle() != null && book.getTitle().length() > 45) {
				return "Book Title cannot be empty and should be 45 char in length";
			}
			book.setBookId(bdao.addBookWithPk(book));
			
//			for (Author a : book.getAuthors()) {
//				adao.addBookAuthors(book.getBookId(), a.getAuthorId());
//			}
			// Do the same for genres/branches etc.
			// for(Author a: book.getAuthors()) {
			// adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			// }
			conn.commit();
			return "Book added sucessfully";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				conn.rollback();
			}
			return "Unable to add book - contact admin.";
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
	
	

	public List<Book> getBooks(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
			if (searchString != null) {
				return bdao.readAllBooksByName(searchString);
			} else {
				return bdao.readAllBooks();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Publisher> getPublishers(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			PublisherDAO pdao = new PublisherDAO(conn);
			if (searchString != null) {
				return pdao.readAllPublishers(searchString);
			} else {
				return pdao.readAllPublishers();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Author> getAuthors(String searchString) {
		try(Connection conn = conUtil.getConnection()) {
			AuthorDAO adao = new AuthorDAO(conn);
			if (searchString != null) {
				return adao.readAllAuthorsByName(searchString);
			} else {
				return adao.readAllAuthors();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Genre> getGenres(){
		try(Connection conn = conUtil.getConnection()) {
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readAllGenres();
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void updateBookName(Book bBook) {
		try(Connection conn = conUtil.getConnection()) {
			BookDAO bdao = new BookDAO(conn);
				 bdao.updateBook(bBook);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
	

}