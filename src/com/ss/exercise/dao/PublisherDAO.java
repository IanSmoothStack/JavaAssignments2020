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
 * @author Ian
 *
 */
public class PublisherDAO extends BaseDAO<Publisher> {
	
	public PublisherDAO(Connection conn) {
		super(conn);
	}
	
	public List<Publisher> readAllPublishers() throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_publisher", null);
	}
	
	public List<Publisher> readAllPublishers(String searchString) throws SQLException, ClassNotFoundException {
		searchString = "%"+searchString+"%";
		return read("SELECT * FROM tbl_publisher WHERE publisherName LIKE ?", new Object[] {searchString});
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
