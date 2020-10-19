/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Borrower;

/**
 * @author Ian
 *
 */
public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}
	
//	public List<Branch> readAllBranchesByName(String searchString) throws SQLException, ClassNotFoundException {
//	searchString = "%"+searchString+"%";
//	return read("SELECT * FROM tbl_library_branch WHERE branchName LIKE ?", new Object[] {searchString});
//}
	public List<Borrower> readBorrowerById(int cardNo)throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_borrower WHERE cardNo ="+cardNo,null);
	}

	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {

		List<Borrower> borrower = new ArrayList<>();
		//BorrowerDAO bdao= new BorrowerDAO(conn);
		while(rs.next()) {
			Borrower b = new Borrower(rs.getInt("cardNo"), rs.getString("name"), rs.getString("address"), rs.getString("phone"));
			borrower.add(b);
		}
		return borrower;
	}

}
