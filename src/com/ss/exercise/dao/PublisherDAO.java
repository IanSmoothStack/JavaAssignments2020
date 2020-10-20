/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Publisher;

/**
 * @publisher Ian
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> {
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_publisher", null);
	}
	
	public List<Publisher> readAllBookPublishers() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_publisher", null);
	}
	
	public List<Publisher> readAllPublishers(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_publisher WHERE publisherName LIKE ?", new Object[] {searchString});
	}
	
//	public void addBookGenres(Integer bookId, Integer genreId) throws SQLException, ClassNotFoundException {
//	save("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { bookId, genreId });
//}
	public void addBookPublishers(Integer bookId, Integer pubId)throws SQLException, ClassNotFoundException {
		save("INSERT INTO tbl_book_publisher VALUES (?, ?)", new Object[] { bookId, pubId });
	}
	
	public void deleteBookPublishers(Integer bookId)throws SQLException, ClassNotFoundException {
		save("DELETE FROM tbl_book_publisher WHERE bookId = ?", new Object[] { bookId});
	}

	
//	public void updateAuthor(Author publisher) throws ClassNotFoundException, SQLException {
//	save("UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?",
//			new Object[] { publisher.getAuthorName(), publisher.getAuthorId() });
//}
//
//public void deleteAuthor(Author publisher) throws ClassNotFoundException, SQLException {
//	save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] { publisher.getAuthorId() });
//}
	
	public void updatePublisher(Publisher publisher)throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_publisher SET publisherName = ? WHERE publisherId = ?",
		new Object[] { publisher.getPublisherName(), publisher.getPublisherId() });
	}
	
	public void deletePublisher(Publisher publisher)throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] { publisher.getPublisherId()});
	}
	
	public Integer addPublisherWithPk(Publisher publisher) throws ClassNotFoundException, SQLException {
		return saveWithPk("INSERT INTO tbl_publisher (publisherName) VALUES (?)", new Object[] { publisher.getPublisherName() });
	}
	
	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		 save("INSERT INTO tbl_publisher (publisherName) VALUES (?)", new Object[] { publisher.getPublisherName() });
	}
	
	
	
	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		
		while (rs.next()) {
			Publisher p = new Publisher(rs.getInt("publisherId"), rs.getString("publisherName"), rs.getString("publisherAddress"));
			
			publishers.add(p);
		}
		return publishers;
	}

	

}
