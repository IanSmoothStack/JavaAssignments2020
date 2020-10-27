package com.ss.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ss.lms.dao.AuthorDAO;
import com.ss.lms.dao.BookDAO;
import com.ss.lms.entity.Author;
import com.ss.lms.entity.Book;

public class AdministratorService {

	@Autowired
	public AuthorDAO adao;

	@Autowired
	public BookDAO bdao;

//	@Autowired
//	public GenreDAO adao;	
//
//	@Autowired
//	public BranchDAO adao;	

	@Transactional
	public String addBook(Book book) throws SQLException {
		try {
			if (book.getTitle() != null && book.getTitle().length() > 45) {
				return "Book Title cannot be empty and should be 45 char in length";
			}
			book.setBookId(bdao.addBookWithPk(book));
			for (Author a : book.getAuthors()) {
				adao.addBookAuthors(book.getBookId(), a.getAuthorId());
			}
			return "Book added sucessfully";
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "Unable to add book - contact admin.";
		}
	}

	public List<Book> getBooks(String searchString) {
		try {
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

}