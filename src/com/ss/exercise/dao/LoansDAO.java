/**
 * 
 */
package com.ss.exercise.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.exercise.entity.Loans;

/**
 * @author Ian
 *
 */
public class LoansDAO extends BaseDAO<Loans> {

	public LoansDAO(Connection conn) {
		super(conn);
	}

	public void addLoan(Loans loan) throws ClassNotFoundException, SQLException {
		save("INSERT INTO tbl_book_loans VALUES ("+loan.getBookId()+", "+loan.getBranchId()+", "+loan.getCardNo()+", '"
	+loan.getDateOut()+"', '"+loan.getDueDate()+"', '"+loan.getDateIn()+"')", null);
		conn.commit();
	}

	
	public List<Loans> readAllLoans()throws SQLException, ClassNotFoundException {
		return read("SELECT * FROM tbl_book_loans",null);
	}

	public void updateLoan( Loans loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dateIn = '"+ loan.getDateIn() +"' WHERE bookId = "+loan.getBookId()
		+" AND branchId = "+loan.getBranchId()+" AND cardNo = "+loan.getCardNo()+" AND dateOut = '"+loan.getDateOut()+"'", null);
		
	}
	
	public void updateLoanOverride( Loans loan) throws ClassNotFoundException, SQLException {
		save("UPDATE tbl_book_loans SET dueDate = '"+ loan.getDueDate() +"' WHERE bookId = "+loan.getBookId()
		+" AND branchId = "+loan.getBranchId()+" AND cardNo = "+loan.getCardNo()+" AND dateOut = '"+loan.getDateOut()+"'", null);
		
	}
	

	@Override
	public List<Loans> extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		List<Loans> loans = new ArrayList<>();
		while(rs.next()) {
			Loans l = new Loans(rs.getInt("bookId"), rs.getInt("branchId"),rs.getInt("cardNo"), rs.getDate("dateOut"),rs.getDate("dueDate"),rs.getDate("dateIn") );
			loans.add(l);
		}
		return loans;
	}

}
