/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.NumberOfCopies;

/**
 * @author Ian
 *
 */
public class NumberOfCopiesDAO extends BaseDAO<NumberOfCopies> {

	public NumberOfCopiesDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	public List<NumberOfCopies> getNumOfBookCopies(int bookId, int branchId) throws ClassNotFoundException, SQLException {
		return read("SELECT noOfCopies FROM tbl_book_copies WHERE bookId ="+bookId+" AND branchId = "+branchId, null );
	}
	
	public void updateNumOfCopies(int bookId, int branchId, int newNumOfCopies) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies ="+ newNumOfCopies+" WHERE bookId = "+bookId+" AND branchId = "+branchId,
				null);
		conn.commit();
	}
	
	
	public void addCopies(int bookId, int branchId, int newNumOfCopies ) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_copies VALUES ("+bookId+", "+branchId+", "+newNumOfCopies+")", null);
		conn.commit();
	}
	

	@Override
	public List<NumberOfCopies> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<NumberOfCopies> numOfCopies = new ArrayList<>();
		while (rs.next()) {
			numOfCopies.add(new NumberOfCopies(rs.getInt("noOfCopies")));
			//also populate the books written by this Author
		}
		return numOfCopies;
	}

}
