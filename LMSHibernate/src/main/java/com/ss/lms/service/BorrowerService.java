package com.ss.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.lms.entity.Borrower;
import com.ss.lms.repo.BorrowerRepo;

@RestController
public class BorrowerService {
	
//	@Autowired
//	public BorrowerDAO bdao;	
	
	@Autowired
	BorrowerRepo borepo;

	
	
	
	public Borrower getBorrowerById(int cardNo) throws SQLException{
		List<Borrower> borrowers = borepo.readBorrowersByCardNo(cardNo);
		if(!borrowers.isEmpty())
			return borrowers.get(0);
		return null;
		
	}
	
	
	///CheckoutBook loan
	///ReturnBook loan
	
	
	
	
//	
//	@RequestMapping(value = "/getBorrowersByQuery", method = RequestMethod.GET, produces = "application/json")
//	public List<Borrower> getBorrowersByQuery(@RequestParam String searchString) {
//		List<Borrower> borrowers = new ArrayList<>();
//		if (searchString != null && searchString.length() > 0) {
//				borrowers = brepo.readBorrowersByName(searchString);
//		} else {
//				borrowers = brepo.findAll();
//		}
//		return borrowers;
//	}
//	
//	
//
//	@RequestMapping(value = "/getAllBorrowers", method = RequestMethod.GET, produces = "application/json")
//	public List<Borrower> getAllBorrowers() {
//		List<Borrower> borrowers = new ArrayList<>();
//		borrowers = brepo.findAll();
//		return borrowers;
//	}
	
	
	
//	public List <Borrower> getBorrower(int cardNo) {
//	try(Connection conn = conUtil.getConnection()) {
//		BorrowerDAO bdao = new BorrowerDAO(conn);
//		List<Borrower> bList =bdao.readBorrowerById(cardNo);
//		return bList; 
//		
//	} catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//		return null;
//	}
//
//}
//public List <Loans> getAllLoans(){
//	try(Connection conn = conUtil.getConnection()) {
//	LoansDAO ldao = new LoansDAO(conn);
//	List<Loans> lList = ldao.readAllLoans();
//	return lList;
//	}catch (ClassNotFoundException | SQLException e) {
//		e.printStackTrace();
//		return null;
//	}
//}
//
//public void addLoan(Loans loan) {
//	try(Connection conn = conUtil.getConnection()) {
//		LoansDAO ldao = new LoansDAO(conn);
//		//Need to check if book was previously checked out by borrower already if so update instead of add.
//		ldao.addLoan(loan);
//		
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			 System.out.println(" success");
//		}
//}
//public void updateLoan(Loans loan) {
//	try(Connection conn = conUtil.getConnection()) {
//		LoansDAO ldao = new LoansDAO(conn);
//		ldao.updateLoan(loan);
//		conn.commit();
//		 System.out.println(" success");
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//}
//
//public void updateOldLoan(Loans loan) {
//	try(Connection conn = conUtil.getConnection()) {
//		LoansDAO ldao = new LoansDAO(conn);
//		///Better way to do this.
//		ldao.updateLoanDateIn(loan);
//		ldao.updateLoanCheckout(loan);
//		ldao.updateLoanOverride(loan);
//		conn.commit();
//		 System.out.println(" success");
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//}
//
//public void updateLoanOverride(Loans loan) {
//	try(Connection conn = conUtil.getConnection()) {
//		LoansDAO ldao = new LoansDAO(conn);
//		ldao.updateLoanOverride(loan);
//		conn.commit();
//		 System.out.println(" success");
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//}
//
//public boolean doesLoanExist(Loans loan) {
//	try(Connection conn = conUtil.getConnection()) {
//		LoansDAO ldao = new LoansDAO(conn);
//		List<Loans> l2 = ldao.checkAllLoans(loan);
//		if(l2.isEmpty()) {
//			return false;
//		}
//		return true;
//		 //System.out.println(" success");
//		}catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//}

}
