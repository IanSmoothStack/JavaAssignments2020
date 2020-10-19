/**
 * 
 */
package com.ss.exercise.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.Loans;
import com.ss.exercise.entity.Publisher;
import com.ss.exercise.service.AdministratorService;
import com.ss.exercise.service.BorrowerService;
import com.ss.exercise.service.LibrarianService;

/**
 * @author Ian
 *
 */
public class AdminUi {
	
	public void adminMenu() {
		System.out.println("Please enter the number of what you wish to edit");
		System.out.println("1) Books");
		System.out.println("2) Authors");
		System.out.println("3) Genres");
		System.out.println("4) Publishers");
		System.out.println("5) Branches");
		System.out.println("6) Borrowers");
		System.out.println("7) Loans");
		
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
		switch (userIn) {
		case 1:
			System.out.println("");
			adminBooks();
			break;
		case 2:
			System.out.println("Not rdy yet");
			break;
		case 3:
			System.out.println("Not rdy yet");
			break;
		case 4:
			System.out.println("Publishers");
			showPublishers();
			break;
		case 5:
			System.out.println("Not rdy yet");
			break;
		case 6:
			System.out.println("Not rdy yet");
			break;
		case 7:
			System.out.println("");
			adminLoans();
			break;
		default:
				System.out.println("Error with selection");
		}
	}
	
	public void adminBooks() {
		//System.out.println("What would you wish to do with BOOKS?");
		actionList("Book");
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
		
		switch (userIn) {
		case 1:
			System.out.println("");
			addBook();
			break;
		case 2:
			System.out.println("update not rdy yet");
			updateBook();
			break;
		case 3:
			System.out.println("Remove not rdy yet");
			break;
		case 4:
			System.out.println("");
			showBooks();
			break;
			default:
				System.out.println("Error with selection");
		}
	}
	
	public void adminAuthors() {
		
	}
	public void adminGenres() {
		
	}
	public void adminPublishers() {
	
	}
	public void adminBranches() {
		
	}
	public void adminBorrowers() {
	
	}
	public void adminLoans() {
		System.out.println("What do you wish to do with loans?");
		System.out.println("1) Read all");
		int userIn = scanner();
		switch(userIn) {
		case 1:
			System.out.println("");
			loansReadAll();
			break;
		case 2:
			System.out.println("");
			break;
		default:
			System.out.println("Error with selection");
		}
	}
	
	public void actionList(String name) {
		System.out.println("What would you wish to do with "+ name +"?");
		System.out.println("1)Add");
		System.out.println("2)Update");
		System.out.println("3)Delete");
		System.out.println("4)Read");
	}
	
	public void showBooks() {
		AdministratorService adminService = new AdministratorService();
		List<Book> books = adminService.getBooks(null);
		for (Book b : books) {
			System.out.println("Book Title: " + b.getTitle());
		}
	}
	
	public void showPublishers() {
		AdministratorService adminService = new AdministratorService();
		List<Publisher> publishers = adminService.getPublishers(null);
		for (Publisher p : publishers) {
			System.out.println("PublisherName: " + p.getPublisherName());
		}
	}
	
	
	
	public void addBook() {
		System.out.println("Enter the book title you want");
		Scanner sc = new Scanner(System.in); 
		String userIn = "placeholder";
		try {
			userIn= sc.next();	
		}
		catch(InputMismatchException exception)
		{
			System.out.println("That was not a valid input");
			sc.next();
		}
		AdministratorService adminService = new AdministratorService();
		Book book = new Book(222, userIn, 1);
		
		
		List<Author> a1 = adminService.getAuthors("Bill Sanders");
		
		book.setAuthors(a1);
		try {
			String log = adminService.addBook(book);
			System.out.println(log);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//showBooks();
		adminBooks();
	}
	
	
 public void updateBook(){
	 AdministratorService adminService = new AdministratorService();
		List<Book> bList = adminService.getBooks(null);
		int i =0;
		for(Book aBook :bList) {
			i++;
			System.out.println(i+") "+aBook.getTitle()+", "+ aBook.getAuthors()+", "+aBook.getBookId());
		}
		System.out.println((i+1)+") Quit to previous");

		int userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
		}
		Book selectedBook = bList.get(userIn-1);
		System.out.println("You have selected to update the book : "+ selectedBook.getTitle()+ ".");
		System.out.println("What do you wish to update about "+selectedBook.getTitle()+"?");
		System.out.println("1) Title");
		System.out.println("2) Author");
		System.out.println("3) Genre");
		System.out.println("4) Quit");
		userIn = scanner();
		switch (userIn) {
		case 1:
			System.out.println("");
			updateBookTitle(selectedBook);
			adminMenu();
			break;
		case 2:
			System.out.println("");
			adminMenu();
			break;
		case 3:
			adminMenu();
			break;
		case 4:
			adminMenu();
			break;
	
		default:
				System.out.println("Error with selection");
		}
		
		
	}

 public void updateBookTitle(Book aBook) {
	 AdministratorService adminService = new AdministratorService();
	 System.out.println("Enter in the new title for the book previously know as "+ aBook.getTitle());
	 String userIn = scannerS();
	 aBook.setTitle(userIn);
	 adminService.updateBookName(aBook);
	 
	 
 }
 
 public void loansReadAll() {
	 BorrowerService bS = new BorrowerService();
		List<Loans> lList =bS.getAllLoans();
		for(Loans aLoan : lList) {
			System.out.println("Loan: bkI "+ aLoan.getBookId()+ " brnI "+aLoan.getBranchId()+" cN "+aLoan.getCardNo());
			System.out.println("      Out "+ aLoan.getDateOut()+ " Due "+aLoan.getDueDate()+" In "+aLoan.getDateIn());
		}
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
			userIn= sc.next();	
		}
		catch(InputMismatchException exception)
		{
			System.out.println("That was not a valid input");
			sc.next();
		}
		return userIn;
	}
	
	
}
