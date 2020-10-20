/**
 * 
 */
package com.ss.exercise.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Borrower;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.Loans;
import com.ss.exercise.entity.NumberOfCopies;
import com.ss.exercise.service.AdministratorService;
import com.ss.exercise.service.BorrowerService;
import com.ss.exercise.service.LibrarianService;

import java.util.List;

/**
 * @author Ian
 *
 */
public class BorrowerUi {
	
	public void borrowerMenu(){
		BorrowerService borrowerService = new BorrowerService();
		List <Borrower> bList;
		do{
		System.out.println("Please enter your card number (or enter 0 to return)");
		int cardNum = scanner();
		if(cardNum==0) {
			LibraryUi lU = new LibraryUi();
			lU.main(null);
			System.exit(0);
		}
		 bList = borrowerService.getBorrower(cardNum);
		if(bList.isEmpty())
		{
			System.out.println("There are no users with that number");		
			System.out.println("");	
			
		}

		}
		while(bList.isEmpty());
		borrow1(bList.get(0));
	}

	
	public void borrow1(Borrower bor) {
		System.out.println("Welcome "+bor.getName());
		System.out.println("What would you like to do?");
		System.out.println("1) Check out a book.");
		System.out.println("2) Return a book.");
		System.out.println("3) Quit to previous.");
		int userIn = scanner();
		switch(userIn) {
		case 1:
			System.out.println("");
			borCheckOut(bor);
			break;
		case 2:
			System.out.println("");
			borReturn(bor);
			break;
		case 3:
			System.out.println("");
			LibraryUi libraryUi = new LibraryUi();
			libraryUi.main(null);
			return;
			//break;
		
		default:
			System.out.println("Error in choice");
		}
	}
	
	public void borCheckOut(Borrower bor) {
	Branch userBranch =pickBranch();
	System.out.println("These are the books you may currently checkout at the "+ userBranch.getBranchName()+ " branch.");
	////Show available books
	AdministratorService adminService = new AdministratorService();
	LibrarianService librarianService = new LibrarianService();
	List<Book> bList = adminService.getBooks(null);
	int i =0;
	int branchId = userBranch.getBranchId();
	int bookId;
	List<Book> aBooks = new ArrayList<>();
	for(Book aBook :bList) {
		
		//System.out.println(i+") "+aBook.getTitle()+", "+ aBook.getAuthors()+", "+aBook.getBookId());
		bookId=aBook.getBookId();
		List<NumberOfCopies> noc =librarianService.getNumberOfCopies(bookId, branchId);
			
			if(!noc.isEmpty()) {
				aBooks.add(aBook);
			}
	}
	for(Book aBook :aBooks) {
		i++;
		bookId=aBook.getBookId();
		List<NumberOfCopies> noc =librarianService.getNumberOfCopies(bookId, branchId);
		NumberOfCopies numC =noc.get(0);
		
		System.out.println(i+") "+aBook.getTitle()+" ("+ numC.getNumOfCopies()+" Copies)");
		
	}
	System.out.println((i+1)+") Quit to cancel operation.");
	int userIn = scanner();
	
	if(userIn == i+1) {
		borrow1(bor);
		return;
	}
	
	Book userBook = aBooks.get(userIn-1);
	System.out.println("You have selected the book "+userBook.getTitle());
	 bookId= userBook.getBookId();
	 int cardNo = bor.getCardNo();
	 
	 Date dateOut = new Date(System.currentTimeMillis());
	 Calendar cal = Calendar.getInstance();
	 cal.setTime(dateOut);
	 cal.add(Calendar.DAY_OF_YEAR,7);
	 cal.set(Calendar.HOUR_OF_DAY, 0);
	 cal.set(Calendar.MINUTE, 0);
	 cal.set(Calendar.SECOND, 0);
	 cal.set(Calendar.MILLISECOND, 0);
	 Date dueDate = new Date(cal.getTimeInMillis());
	 Calendar cal2 = Calendar.getInstance();
	 cal2.set(1111, 10, 11);
	 Date dateIn = new Date(cal2.getTimeInMillis());

	 
	 System.out.println("DateOut: "+dateOut);
	 System.out.println("DueDate: "+dueDate);
	 System.out.println("DateIn: "+dateIn);
	 
	 Loans loan = new Loans(bookId, branchId, cardNo, dateOut,dueDate,dateIn);
		BorrowerService bS = new BorrowerService();
		bS.addLoan(loan);
		
		
		List<NumberOfCopies> noc =librarianService.getNumberOfCopies(loan.getBookId(), loan.getBranchId());
		int copyNum=0;
		NumberOfCopies noc2;
		if(!noc.isEmpty()) {
			noc2=noc.get(0);
			copyNum=noc2.getNumOfCopies();
		}
		 librarianService.updateNumOfCopies(loan.getBookId(), loan.getBranchId(), copyNum-1);
	System.out.println("You have checked out " +userBook.getTitle()+" it is due at "+dueDate);
	System.out.println("");
		borrowerMenu();
	
	}
	
	public void borReturn(Borrower bor) {
		Branch userBranch =pickBranch();
		////Display all checked out books
		 BorrowerService bS = new BorrowerService();
		 Calendar cal2 = Calendar.getInstance();
		 cal2.set(1111, 10, 11);
		 int i = 0;
		 Date dateIn = new Date(cal2.getTimeInMillis());
		 //System.out.println("Date: "+dateIn);

		 List<Loans> userLoans = new ArrayList<>();
			List<Loans> lList =bS.getAllLoans();
			for(Loans aLoan : lList) {
				//System.out.println("      Out "+ aLoan.getDateOut()+ " Due "+aLoan.getDueDate()+" In "+aLoan.getDateIn());
				// System.out.println(aLoan.getDateOut()+" "+aLoan.getDateIn().compareTo(dateIn));
				if(aLoan.getBranchId()==userBranch.getBranchId() && bor.getCardNo()==aLoan.getCardNo()&&aLoan.getDateIn().compareTo(dateIn)!= 1 ) {
					
					userLoans.add(aLoan);
				}
			}
			if(userLoans.isEmpty()) {
				System.out.println("You have no checked out books at this branch");
			}
			else {
				for(Loans aLoan : userLoans) {
					i++;
					System.out.println(i+") Loan: bookId "+ aLoan.getBookId()+ " branchId "+aLoan.getBranchId()+" cardNo "+aLoan.getCardNo());
					System.out.println("      Out "+ aLoan.getDateOut()+ " Due "+aLoan.getDueDate()+" In "+aLoan.getDateIn());
				}
				System.out.println((i+1)+") Quit to previous");
				int userIn = scanner();
				if(userIn == i+1) {
					borrowerMenu();
					return;
				}
				//was i-2 but was giving out of bounds exception
				Loans sLoan = userLoans.get(i-1);
				System.out.println("You have selected "+ "Out "+ sLoan.getDateOut()+ " Due "+sLoan.getDueDate()+" In "+sLoan.getDateIn());
				Date currentTime = new Date(System.currentTimeMillis());
				sLoan.setDateIn(currentTime);
				bS.updateLoan(sLoan);
				LibrarianService librarianService = new LibrarianService();
				
				List<NumberOfCopies> noc =librarianService.getNumberOfCopies(sLoan.getBookId(), sLoan.getBranchId());
				int copyNum=0;
				NumberOfCopies noc2;
				if(!noc.isEmpty()) {
					noc2=noc.get(0);
					copyNum=noc2.getNumOfCopies();
				}
				 librarianService.updateNumOfCopies(sLoan.getBookId(), sLoan.getBranchId(), copyNum+1);
				 borrowerMenu();
			}
	}
	
	
	
	public Branch pickBranch() {
		System.out.println("Pick the branch you wish to check out from.");
		LibrarianService librarianService = new LibrarianService();
		List<Branch> bList = librarianService.getBranches(null);
		int i =0;
		for(Branch aBranch :bList) {
			i++;
			System.out.println(i+") "+aBranch.getBranchName()+", "+ aBranch.getBranchAddress());
		}
		System.out.println((i+1)+") Quit to previous");

		int userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			borrowerMenu();
		}
		Branch userBranch = bList.get(userIn-1);
		System.out.println("You have selected the "+ userBranch.getBranchName()+ " branch.");
		return userBranch;
	}
	
	
	
	public int scanner() {
		Scanner sc = new Scanner(System.in); 
		int userIn = 0;
		try {
			userIn= sc.nextInt();	
		}
		catch(InputMismatchException exception)
		{
			System.out.println("That was not a valid input");
			sc.next();
		}
		return userIn;
	}
	
	///Overload later?
	public String scannerS() {
		Scanner sc = new Scanner(System.in); 
		String userIn = "placeholder";
		try {
			userIn= sc.nextLine();	
		}
		catch(InputMismatchException exception)
		{
			System.out.println("That was not a valid input");
			sc.next();
		}
		return userIn;
	}
}
