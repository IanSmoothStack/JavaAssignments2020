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
import com.ss.exercise.entity.Branch;

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
	
	
	public List<Borrower> readAllBorrowers()throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_borrower", null);
	}
	
	
	public void deleteBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("DELETE FROM tbl_borrower  WHERE cardNo = ?", new Object[] { borrower.getCardNo() });
	}
	
	public Integer addBorrowerWithPk(Borrower borrower) throws ClassNotFoundException, SQLException {
	return saveWithPk("INSERT INTO tbl_borrower (name) VALUES (?)", new Object[] { borrower.getName() });
	}
	
	public void addBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
	 save("INSERT INTO tbl_borrower (name) VALUES (?)", new Object[] { borrower.getName() });
	}
	public void updateFullBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_borrower SET name = ? AND address = ? AND phone = ? WHERE cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
		}
	
	public void updateBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_borrower SET name = ? WHERE cardNo = ?",
				new Object[] { borrower.getName(), borrower.getCardNo() });
		conn.commit();
	}
	public void updatePhoneBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_borrower SET phone = ? WHERE cardNo = ?",
				new Object[] { borrower.getPhone(), borrower.getCardNo() });
		conn.commit();
	}
	public void updateAddressBorrower(Borrower borrower) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_borrower SET address = ? WHERE cardNo = ?",
				new Object[] { borrower.getAddress(), borrower.getCardNo() });
		conn.commit();
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
