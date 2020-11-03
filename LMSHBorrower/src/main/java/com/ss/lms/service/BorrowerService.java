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

import com.ss.lms.entity.Book;
import com.ss.lms.entity.BookCopies;
import com.ss.lms.entity.BookLoans;
import com.ss.lms.entity.Borrower;
import com.ss.lms.entity.Branch;
import com.ss.lms.repo.BookCopiesRepo;
import com.ss.lms.repo.BookLoansRepo;
import com.ss.lms.repo.BookRepo;
import com.ss.lms.repo.BorrowerRepo;
import com.ss.lms.repo.BranchRepo;

@RestController
public class BorrowerService {
		
	
	@Autowired
	BorrowerRepo borepo;

	@Autowired
	public BookLoansRepo blrepo;	
	
	
	@Autowired
	public BranchRepo brrepo;	
	
	@Autowired
	public BookRepo brepo;	
	
	@Autowired
	public BookCopiesRepo bcrepo;	
		
	
	
	
	
	
	@RequestMapping(value = "/getBorrowerByCardNo", method = RequestMethod.GET, produces = "application/json")
	public Borrower getBorrowerByCardNo(@RequestBody Borrower borrower) throws SQLException { 
		if(borrower.getCardNo() == null)
			return null;
		Borrower bor = getBorrowerById(borrower.getCardNo());
		return bor;
	}
	
	@Transactional
	@RequestMapping(value = "/checkOutBook", method = RequestMethod.POST, produces = "application/json")
	public List<BookLoans> checkOutBook(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		addBookLoan(bookLoans);
		BookCopies bookCopy = new BookCopies();
		bookCopy.getId().setBookId(bookLoans.getId().getBookId());
		bookCopy.getId().setBranchId(bookLoans.getId().getBranchId());
		bookCopy = getBookCopyNo(bookCopy);
		bookCopy.setNumOfCopies(bookCopy.getNumOfCopies()-1);
		updateBookCopies(bookCopy);
		
		return getAllBookLoans();
	}
	
	@Transactional
	@RequestMapping(value = "/returnBook", method = RequestMethod.POST, produces = "application/json")
	public List<BookLoans> returnBook(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		updateBookLoan(bookLoans);
		BookCopies bookCopy = new BookCopies();
		bookCopy.getId().setBookId(bookLoans.getId().getBookId());
		bookCopy.getId().setBranchId(bookLoans.getId().getBranchId());
		bookCopy = getBookCopyNo(bookCopy);
		bookCopy.setNumOfCopies(bookCopy.getNumOfCopies()+1);
		updateBookCopies(bookCopy);
		
		return getAllBookLoans();
	}
	
	
	
	///This will be called when the borrower checks out a book (along with updateBookCopies is libService)
	@Transactional
	@RequestMapping(value = "/addBookLoan", method = RequestMethod.POST, produces = "application/json")
	public List<BookLoans> addBookLoan(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		blrepo.save(bookLoans);
		
		return getAllBookLoans();
	}
	
	
	///This will be called when the borrower returns a book (along with updateBookCopies is libService)
	@Transactional
	@RequestMapping(value = "/updateBookLoan", method = RequestMethod.POST, produces = "application/json")
	public List<BookLoans> updateBookLoan(@RequestBody BookLoans bookLoans) throws SQLException { 
		
		
		if(bookLoans.getId().getBookId() == null|bookLoans.getId().getBranchId() == null |bookLoans.getId().getCardNo() == null)
			return null;
		int bookId = bookLoans.getId().getBookId();
		int branchId = bookLoans.getId().getBranchId();
		int cardNo = bookLoans.getId().getCardNo();
		BookLoans oldLoan = getBookLoansById(bookId, branchId, cardNo);
		
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
		
		return getAllBookLoans();
	}
	
	
	public Borrower getBorrowerById(int cardNo) throws SQLException{
		List<Borrower> borrowers = borepo.readBorrowersByCardNo(cardNo);
		if(!borrowers.isEmpty())
			return borrowers.get(0);
		return null;
		
	}
	
	
	
	
	
	//////////////////////////
	
	@RequestMapping(value = "/getBranchesByQuery", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getBranchesByQuery(@RequestParam String searchString) {
		List<Branch> branches = new ArrayList<>();
		if (searchString != null && searchString.length() > 0) {
				branches = brrepo.readBranchesByName(searchString);
		} else {
				branches = brrepo.findAll();
		}
		return branches;
	}
	
	///make sure to hide branchId in borrower version
	@RequestMapping(value = "/getAllBranches", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> getAllBranches() {
		List<Branch> branchs = new ArrayList<>();
		branchs = brrepo.findAll();
		return branchs;
	}
	
	
	@RequestMapping(value = "/getAllBookCopies", method = RequestMethod.GET, produces = "application/json")
	public List<BookCopies> getAllBookCopies() {
		List<BookCopies> bookCopies = new ArrayList<>();
		bookCopies = bcrepo.findAll();
		return bookCopies;
	}
	
	
	@RequestMapping(value = "/getAllBookLoans", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> getAllBookLoans() {
		List<BookLoans> bookLoans = new ArrayList<>();
		bookLoans = blrepo.findAll();
		return bookLoans;
	}
	
	@RequestMapping(value = "/getBranchBooks", method = RequestMethod.GET, produces = "application/json")
	public List<Book> getBranchBooks(@RequestBody Branch sBranch) throws SQLException { 
		List<Book> books = new ArrayList<>();
		if(sBranch.getBranchId()==null)
			return null;
		int branchId = sBranch.getBranchId();
		List<BookCopies> bc = new ArrayList<>();
		bc = bcrepo.readBookCopiesByBranchId(branchId);
		
		for (BookCopies a : bc) {
			books.add(getBookById(a.getId().getBookId()));
		}		
		return books;
	}
	
	
	@RequestMapping(value = "/getBookCopiesByBranchId", method = RequestMethod.GET, produces = "application/json")
	public List<BookCopies> getBookCopiesByBranchId(@RequestBody Branch sBranch) throws SQLException { 
		if(sBranch.getBranchId()==null)
			return null;
		int branchId = sBranch.getBranchId();
		List<BookCopies> bc = new ArrayList<>();
		bc = bcrepo.readBookCopiesByBranchId(branchId);	
		return bc;
	}
	
	@RequestMapping(value = "/getBookCopyNo", method = RequestMethod.GET, produces = "application/json")
	public BookCopies getBookCopyNo(@RequestBody BookCopies bookCopies) throws SQLException { 
		if(bookCopies.getId().getBranchId()==null)
			return null;
		int branchId = bookCopies.getId().getBranchId();
		if(bookCopies.getId().getBookId()==null)
			return null;
		int bookId = bookCopies.getId().getBranchId();
		BookCopies bc = getBookCopiesById(bookId, branchId);	
		return bc;
	}
	
	@Transactional
	@RequestMapping(value = "/addBookCopies", method = RequestMethod.POST, produces = "application/json")
	public List<BookCopies> addBookCopies(@RequestBody BookCopies bookCopies ) throws SQLException { 
	
	//	if(bookCopies.getBranchId() == null)
	//		return null;
		int branchId = bookCopies.getId().getBranchId();
		bcrepo.save(bookCopies);
		List<BookCopies> bc = new ArrayList<>();
		bc = bcrepo.readBookCopiesByBranchId(branchId);	
		return bc;
	}
	
	
	@Transactional
	@RequestMapping(value = "/updateBookCopies", method = RequestMethod.POST, produces = "application/json")
	public List<BookCopies> updateBookCopies(@RequestBody BookCopies bookCopies ) throws SQLException { 
	
		int branchId = bookCopies.getId().getBranchId();
		bcrepo.save(bookCopies);
		List<BookCopies> bc = new ArrayList<>();
		bc = bcrepo.readBookCopiesByBranchId(branchId);	
		return bc;
	}
	
	///Shorter version of body u need to send. Just CardNo, not wrapped in id
	@RequestMapping(value = "/getBookLoansByCardNo", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> getBookLoansByCardNo(@RequestBody Borrower borrower) throws SQLException { 
		if(borrower.getCardNo()==null)
			return null;
		int cardNo = borrower.getCardNo();
		List<BookLoans> bl = new ArrayList<>();
		bl = blrepo.readBookLoansByCardNo(cardNo);	
		return bl;
	}
	
	///// cardNo needs to be wrapped in id
	@RequestMapping(value = "/getBookLoansByCardNo2", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> getBookLoansByCardNo2(@RequestBody BookLoans loan) throws SQLException { 
		if(loan.getId().getCardNo()==null)
			return null;
		int cardNo = loan.getId().getCardNo();
		List<BookLoans> bl = new ArrayList<>();
		bl = blrepo.readBookLoansByCardNo(cardNo);	
		return bl;
	}
	
	@RequestMapping(value = "/getBookLoansByBranchId", method = RequestMethod.GET, produces = "application/json")
	public List<BookLoans> getBookLoansByBranchId(@RequestBody BookLoans loan) throws SQLException { 
		List<Book> books = new ArrayList<>();
		if(loan.getId().getBranchId()==null)
			return null;
		int branchId = loan.getId().getBranchId();
		List<BookLoans> bl = new ArrayList<>();
		bl = blrepo.readBookLoansByBranchId(branchId);	
		return bl;
	}
	
	
	
	
	public Branch getBranchById(int branchId) throws SQLException{
		List<Branch> branchs = brrepo.readBranchesById(branchId);
		if(!branchs.isEmpty())
			return branchs.get(0);
		return null;
		
	}
	
	

	
	
	
	public Book getBookById(int bookId) throws SQLException{
		List<Book> books = brepo.readBooksById(bookId);
		if(!books.isEmpty())
			return books.get(0);
		return null;
		
	}
	
	
	public BookCopies getBookCopiesById(int bookId, int branchId) throws SQLException{
		List<BookCopies> bookCopies = bcrepo.readBookCopiesById(bookId, branchId);
		if(!bookCopies.isEmpty())
			return bookCopies.get(0);
		return null;
		
	}
	
	
	public BookLoans getBookLoansById(int bookId, int branchId, int cardNo) throws SQLException{
		List<BookLoans> bookLoans = blrepo.readBookLoansById(bookId, branchId, cardNo);
		if(!bookLoans.isEmpty())
			return bookLoans.get(0);
		return null;
		
	}
	


}
