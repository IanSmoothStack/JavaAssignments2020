/**
 * 
 */
package com.ss.exercise.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.exercise.dao.BorrowerDAO;
import com.ss.exercise.dao.LoansDAO;
import com.ss.exercise.dao.NumberOfCopiesDAO;
import com.ss.exercise.entity.Borrower;
import com.ss.exercise.entity.Loans;

/**
 * @author Ian
 *
 */
public class BorrowerService {
	public ConnectionUtil conUtil = new ConnectionUtil();
	
	public List <Borrower> getBorrower(int cardNo) {
		try(Connection conn = conUtil.getConnection()) {
			BorrowerDAO bdao = new BorrowerDAO(conn);
			List<Borrower> bList =bdao.readBorrowerById(cardNo);
			return bList; 
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	public List <Loans> getAllLoans(){
		try(Connection conn = conUtil.getConnection()) {
		LoansDAO ldao = new LoansDAO(conn);
		List<Loans> lList = ldao.readAllLoans();
		return lList;
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addLoan(Loans loan) {
		try(Connection conn = conUtil.getConnection()) {
			LoansDAO ldao = new LoansDAO(conn);
			//Need to check if book was previously checked out by borrower already if so update instead of add.
			ldao.addLoan(loan);
			
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				 System.out.println(" success");
			}
	}
	public void updateLoan(Loans loan) {
		try(Connection conn = conUtil.getConnection()) {
			LoansDAO ldao = new LoansDAO(conn);
			ldao.updateLoan(loan);
			conn.commit();
			 System.out.println(" success");
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void updateOldLoan(Loans loan) {
		try(Connection conn = conUtil.getConnection()) {
			LoansDAO ldao = new LoansDAO(conn);
			///Better way to do this.
			ldao.updateLoanDateIn(loan);
			ldao.updateLoanCheckout(loan);
			ldao.updateLoanOverride(loan);
			conn.commit();
			 System.out.println(" success");
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void updateLoanOverride(Loans loan) {
		try(Connection conn = conUtil.getConnection()) {
			LoansDAO ldao = new LoansDAO(conn);
			ldao.updateLoanOverride(loan);
			conn.commit();
			 System.out.println(" success");
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	public boolean doesLoanExist(Loans loan) {
		try(Connection conn = conUtil.getConnection()) {
			LoansDAO ldao = new LoansDAO(conn);
			List<Loans> l2 = ldao.checkAllLoans(loan);
			if(l2.isEmpty()) {
				return false;
			}
			return true;
			 //System.out.println(" success");
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

}
