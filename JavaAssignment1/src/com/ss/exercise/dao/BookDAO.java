package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.NumberOfCopies;
import com.ss.exercise.entity.Publisher;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void addBook(Book book) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}
	
	public Integer addBookWithPk(Book book) throws ClassNotFoundException, SQLException {
		return saveWithPk("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}
	
	public Integer addBookWithPk2(Book book) throws ClassNotFoundException, SQLException {
		return saveWithPk("INSERT INTO tbl_book (title) VALUES '"+book.getTitle()+"'", null);
	}
	

	public void updateBook(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET title = ? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
		//conn.commit();
	}
	public void updateBookAuthors(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET title = ? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}
	public void updateBookGenres(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET title = ? WHERE bookId = ?",
				new Object[] { book.getTitle(), book.getBookId() });
	}
	public void updateBookPublisher(Book book) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book SET pubId = ? WHERE bookId = ?",
				new Object[] { book.getPubId(), book.getBookId() });
	}

	public void deleteBook(Book book) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book", null);
	}
	
	public List<Book> readAllBooksByName(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_book WHERE title LIKE ?", new Object[] {searchString});
	}
	
	public void addBookAuthors(Integer bookId, Integer authorId) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { bookId, authorId });
	}
	
	
	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { bookId, genreId });
	}
	
	

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		//Look into making this more effecient
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		while (rs.next()) {
//			Book b = new Book(rs.getInt("bookId"), rs.getString("title"),rs.getInt("pubId"));
			Book b = new Book(rs.getInt("bookId"), rs.getString("title"));
			b.setAuthors(adao.read("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));
			b.setGenres(gdao.read("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)", new Object[] {b.getBookId()}));
			List<Publisher> pList =( pdao.read("select * from tbl_publisher where publisherId IN (select publisherId from tbl_book_publisher where bookId = ?)", new Object[] {b.getBookId()}));
			if(!pList.isEmpty())
			b.setPublisher(pList.get(0));
			
			//b.setGenres()
			books.add(b);
		}
		return books;
	}
}