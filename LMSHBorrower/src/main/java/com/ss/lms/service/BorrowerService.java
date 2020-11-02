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

import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.repo.BookLoansRepo;
import com.ss.lms.repo.BorrowerRepo;

@RestController
public class BorrowerService {
		
	
	@Autowired
	BorrowerRepo borepo;

	@Autowired
	public BookLoansRepo blrepo;	
	
	LibrarianService ls = new LibrarianService();
	
	
	
	@RequestMapping(value = "/getBorrowerByCardNo", method = RequestMethod.GET, produces = "application/json")
	public Borrower getBorrowerByCardNo(@RequestBody Borrower borrower) throws SQLException { 
		if(borrower.getCardNo() == null)
			return null;
		Borrower bor = getBorrowerById(borrower.getCardNo());
		return bor;
	}
	
	///This will be called when the borrower checks out a book (along with updateBookCopies is libService)
	@RequestMapping(value = "/addBookLoan", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> addBookLoan(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		blrepo.save(bookLoans);
		
		return ls.getAllBookLoans();
	}
	
	
	///This will be called when the borrower returns a book (along with updateBookCopies is libService)
	@RequestMapping(value = "/updateBookLoan", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> updateBookLoan(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		
		if(bookLoans.getId().getBookId() == null|bookLoans.getId().getBranchId() == null |bookLoans.getId().getCardNo() == null)
			return null;
		int bookId = bookLoans.getId().getBookId();
		int branchId = bookLoans.getId().getBranchId();
		int cardNo = bookLoans.getId().getCardNo();
		BookLoans oldLoan = ls.getBookLoansById(bookId, branchId, cardNo);
		
		if(bookLoans.getDateIn()!=null) {
			oldLoan.setDateIn(bookLoans.getDateIn());
		}
		if(bookLoans.getDateOut()!=null) {
			oldLoan.setDateOut(bookLoans.getDateOut());
		}

		if(bookLoans.getDueDate()!=null) {
			oldLoan.setDueDate(bookLoans.getDueDate());
		}
		
		blrepo.save(bookLoans);
		
		return ls.getAllBookLoans();
	}
	
	
	public Borrower getBorrowerById(int cardNo) throws SQLException{
		List<Borrower> borrowers = borepo.readBorrowersByCardNo(cardNo);
		if(!borrowers.isEmpty())
			return borrowers.get(0);
		return null;
		
	}
	
	


}
