/**
 * 
 */
package com.ss.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Book;

/**
 * @book ppradhan
 *
 */
@Repository
public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>> {

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}

	public Integer addBookWithPk(Book book) throws ClassNotFoundException, SQLException {
		return jdbcTemplate.update("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("UPDATE tbl_book SET bookName = ? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		jdbcTemplate.update("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
		return jdbcTemplate.query("SELECT * FROM tbl_book", this);
	}

	public List<Book> readAllBooksByName(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%" + searchString + "%";
		return jdbcTemplate.query("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] { searchString }, this);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book(rs.getInt("bookId"), rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}