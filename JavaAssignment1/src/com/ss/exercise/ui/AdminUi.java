/**
 * 
 */
package com.ss.exercise.ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import com.ss.exercise.entity.Author;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Borrower;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.Genre;
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
		System.out.println("8) Return to previous");
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
			System.out.println("");
			editAuthors();
			break;
		case 3:
			System.out.println("");
			editGenres();
			break;
		case 4:
			System.out.println("");
			editPublisher();
			//showPublishers();
			break;
		case 5:
			System.out.println("");
			editBranches();
			break;
		case 6:
			System.out.println("Not rdy yet");
			editBorrowers();
			break;
		case 7:
			System.out.println("");
			adminLoans();
			break;
		case 8:
			System.out.println("");
			LibraryUi lU = new LibraryUi();
			lU.main(null);
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
			System.out.println("");
			updateBook();
			break;
		case 3:
			System.out.println("");
			deleteBook();
			break;
		case 4:
			System.out.println("");
			showBooks();
			break;
		 case 5:
			 System.out.println("");
			 adminMenu();
			 break;
			default:
				System.out.println("Error with selection");
		}
	}
	///1-Add 2-Update 3-Delete 4-Read
	public void adminAuthors() {
		int userIn =optionSet1("Authors");
		
	}
	public void adminGenres() {
		int userIn =optionSet1("Genres");
		
	}
	public void adminPublishers() {
		int userIn =optionSet1("Publishers");
	
	}
	public void adminBranches() {
		int userIn =optionSet1("Branches");
		
	}
	public void adminBorrowers() {
		int userIn =optionSet1("Borrowers");
	
	}
	public void adminLoans() {
		System.out.println("What do you wish to do with loans?");
		System.out.println("1) Read all");
		System.out.println("2) Override a due date");
		int userIn = scanner();
		switch(userIn) {
		case 1:
			System.out.println("");
			loansReadAll();
			break;
		case 2:
			System.out.println("");
			overrideLoan();
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
		System.out.println("5)Quit to admin menu");
	}
	
	public void showBooks() {
		AdministratorService adminService = new AdministratorService();
		List<Book> books = adminService.getBooks(null);
		for (Book b : books) {
			List<Author> aList = b.getAuthors();
		 	List<Genre> gList = b.getGenres();
		 	List<String> aNames = new ArrayList<>();
		 	List<String> aGenres = new ArrayList<>();
		 	for(Author author : aList) {
		 		aNames.add(author.getAuthorName());
		 	}
		 	String authorString = String.join(", ", aNames);
		 	
		 	for(Genre genre : gList) {
		 		aGenres.add(genre.getGenreName());
		 	}
		 	String genreString = String.join(", ", aGenres);
		 	Publisher publisher = b.getPublisher();
		 	String pubName ="no publisher read";
		 	if(publisher != null)
		 		pubName=publisher.getPublisherName();
			System.out.println("Book Title: " + b.getTitle());
			System.out.println("    Author(s): "+authorString);
			System.out.println("    Genre(s): "+genreString);
			System.out.println("    Publisher: "+pubName);
			
		}
		System.out.println("Enter in any char to return to adminMenu");
		String s = scannerS();
		adminBooks();
		
	}
	
	public void showPublishers() {
		AdministratorService adminService = new AdministratorService();
		List<Publisher> publishers = adminService.getPublishers(null);
		for (Publisher p : publishers) {
			System.out.println("Publisher: " + p.getPublisherName());
		}
	}
	
	
	
	public void addBook() {
		System.out.println("Enter the book title you want");
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
		AdministratorService adminService = new AdministratorService();
		String title = userIn;
		////Show all authors
		List<Author> aList = adminService.getAuthors(null);
		
//		Book book = new Book(null, userIn, null);
		Book book = new Book(null, title);
		
		
		
		
		//showBooks();
		List<Author> authors = authorSelector();
		
		List<Genre> genres =genreSelector();
		Publisher publisher = publisherSelector();
		System.out.println("You have selected "+publisher.getPublisherName());
		book.setAuthors(authors);
		book.setGenres(genres);
		book.setPublisher(publisher);
		book.setPubId(publisher.getPublisherId());
		try {
			String log = adminService.addBook(book);
			System.out.println(log);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		adminBooks();
	}
	
	
 public void updateBook(){
	 AdministratorService adminService = new AdministratorService();

	 	Book selectedBook = pickABook();
	 	List<Author> aList = selectedBook.getAuthors();
	 	List<Genre> gList = selectedBook.getGenres();
	 	List<String> aNames = new ArrayList<>();
	 	List<String> aGenres = new ArrayList<>();
	 	for(Author author : aList) {
	 		aNames.add(author.getAuthorName());
	 	}
	 	String authorString = String.join(", ", aNames);
	 	
	 	for(Genre genre : gList) {
	 		aGenres.add(genre.getGenreName());
	 	}
	 	String genreString = String.join(", ", aGenres);
	 	Publisher publisher = selectedBook.getPublisher();
	 	String pubName ="no publisher read";
	 	if(publisher != null)
	 		pubName=publisher.getPublisherName();
	 	
		System.out.println("You have selected to update the book : "+ selectedBook.getTitle()+ ".");
		System.out.println("What do you wish to update about "+selectedBook.getTitle()+"?");
		System.out.println("Title: "+selectedBook.getTitle());
		System.out.println(" Authors: "+authorString);
		System.out.println(" Genres: "+genreString);
		System.out.println(" Publisher: "+pubName);
		System.out.println("1) Title");
		System.out.println("2) Author");
		System.out.println("3) Genre");
		System.out.println("4) Publisher");
		System.out.println("5) Quit");
		int userIn = scanner();
		switch (userIn) {
		case 1:
			System.out.println("");
			updateBookTitle(selectedBook);
			adminMenu();
			break;
		case 2:
			System.out.println("");
			updateBookAuthors(selectedBook);
			adminMenu();
			break;
		case 3:
			System.out.println("");
			updateBookGenres(selectedBook);
			adminMenu();
			break;
		case 4:
			System.out.println("");
			updateBookPublisher(selectedBook);
			adminMenu();
			break;
		case 5:
			System.out.println("");
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
	 
	 adminMenu();
 }
 
 
 
 public void updateBookAuthors(Book selectedBook) {
	 AdministratorService adminService = new AdministratorService();
	 List<Author> authors = authorSelector();
	 selectedBook.setAuthors(authors);
	 //adminService.updateBookName(selectedBook);
	 adminService.updateBookAll(selectedBook);
	 adminMenu();
 }
 
 public void updateBookGenres(Book selectedBook) {
	 AdministratorService adminService = new AdministratorService();
	 List<Genre> genres = genreSelector();
	 selectedBook.setGenres(genres);
	 //adminService.updateBookName(selectedBook);
	 adminService.updateBookAll(selectedBook);
	 adminMenu();
 }
 
 public void updateBookPublisher(Book selectedBook) {
	 AdministratorService adminService = new AdministratorService();
	 Publisher publisher = publisherSelector();
	 selectedBook.setPublisher(publisher);
	// adminService.updateBookName(selectedBook);
	 adminService.updateBookAll(selectedBook);
	 adminMenu();
 }
 
 public void deleteBook(){
	 Book selectedBook = pickABook();
	 AdministratorService adminService = new AdministratorService();
	 adminService.deleteBook(selectedBook);
	 adminMenu();
 }
 
 public void editAuthors() {
	 actionList("Authors");
	 int userIn = scanner();
	 switch(userIn) {
	 case 1:
		 System.out.println("");
		 authorAdd();
		 break;
	 case 2:
		 System.out.println("");
		 authorUpdate();
		 break;
	 case 3:
		 System.out.println("");
		 authorDelete();
		 break;
	 case 4:
		 System.out.println("");
		 authorRead();
		 break;
	 case 5:
		 System.out.println("");
		 adminMenu();
		 break;
	 }
 }
 
 
 public void authorAdd() {
	 AdministratorService adminService = new AdministratorService();
	 System.out.println("Enter in the name of this new author.");
	 String newName= scannerS();
	 Author author = new Author(null,newName);
	 adminService.addAuthor(author);
	 adminMenu();
 }
 
 
 public void authorUpdate() {
	 AdministratorService adminService = new AdministratorService();
	 Author author = authorSelector2();
	 System.out.println("Enter in new author name for the author formly known as "+author.getAuthorName());
	 String newName= scannerS();
	 author.setAuthorName(newName);
	 adminService.updateAuthor(author);
	 adminMenu();
 }
 
 
 public void authorDelete() {
	 AdministratorService adminService = new AdministratorService();
	 Author author = authorSelector2();
	 adminService.deleteAuthor(author);
	 adminMenu();
 }
 
 
 public void authorRead() {
		AdministratorService adminService = new AdministratorService();
		List<Author>bList = new ArrayList<>();
		List<Author> aList = adminService.getAuthors(null);	
		for(Author aAuthor :aList) {
			System.out.println(aAuthor.getAuthorName());
		}
		System.out.println("Enter any key to return to previous");
		int userIn = scanner();
		 adminMenu();
 }
 
 
 public void editGenres() {
	 actionList("Genres");
	 int userIn = scanner();
	 switch(userIn) {
	 case 1:
		 System.out.println("");
		 genreAdd();
		 break;
	 case 2:
		 System.out.println("");
		 genreUpdate();
		 break;
	 case 3:
		 System.out.println("");
		 genreDelete();
		 break;
	 case 4:
		 System.out.println("");
		 genreRead();
		 break;
	 case 5:
		 System.out.println("");
		 adminMenu();
		 break;
	 }
 }
 
 
 public void genreAdd() {
	 AdministratorService adminService = new AdministratorService();
	 System.out.println("Enter in the name of this new genre.");
	 String newName= scannerS();
	 Genre genre = new Genre(null,newName);
	 adminService.addGenre(genre);
	 adminMenu();
 }
 
 
 public void genreUpdate() {
	 AdministratorService adminService = new AdministratorService();
	 Genre genre = genreSelector2();
	 System.out.println("Enter in new genre name for the genre formly known as "+genre.getGenreName());
	 String newName= scannerS();
	 genre.setGenreName(newName);
	 adminService.updateGenre(genre);
	 adminMenu();
 }
 
 
 
 public void genreDelete() {
	 AdministratorService adminService = new AdministratorService();
	 Genre genre = genreSelector2();
	 adminService.deleteGenre(genre);
	 adminMenu();
 }
 
 
 public void genreRead() {
		AdministratorService adminService = new AdministratorService();
		List<Genre>bList = new ArrayList<>();
		List<Genre> aList = adminService.getGenres();	
		for(Genre aGenre :aList) {
			System.out.println(aGenre.getGenreName());
		}
		System.out.println("Enter any key to return to previous");
		int userIn = scanner();
		 adminMenu();
}
 
 
 
 
 public void editPublisher() {
	 actionList("Publisher");
	 int userIn = scanner();
	 switch(userIn) {
	 case 1:
		 System.out.println("");
		 publisherAdd();
		 break;
	 case 2:
		 System.out.println("");
		 publisherUpdate();
		 break;
	 case 3:
		 System.out.println("");
		 publisherDelete();
		 break;
	 case 4:
		 System.out.println("");
		 publisherRead();
		 break;
	 case 5:
		 System.out.println("");
		 adminMenu();
		 break;
	 }
 }
 
 
 public void publisherAdd() {
	 AdministratorService adminService = new AdministratorService();
	 System.out.println("Enter in the name of this new publisher.");
	 String newName= scannerS();
	 Publisher publisher = new Publisher(null,newName , null);
	 adminService.addPublisher(publisher);
	 adminMenu();
 }
 
 
 public void publisherUpdate() {
	 AdministratorService adminService = new AdministratorService();
	 Publisher publisher = publisherSelector();
	 System.out.println("Enter in new publisher name for the publisher formly known as "+publisher.getPublisherName());
	 String newName= scannerS();
	 publisher.setPublisherName(newName);
	 adminService.updatePublisher(publisher);
	 adminMenu();
 }
 
 
 
 public void publisherDelete() {
	 AdministratorService adminService = new AdministratorService();
	 Publisher publisher = publisherSelector();
	 adminService.deletePublisher(publisher);
	 adminMenu();
 }
 
 
 public void publisherRead() {

	 	showPublishers();
		System.out.println("Enter any key to return to previous");
		int userIn = scanner();
		 adminMenu();
}
 
 
 
 public void editBranches() {
	 actionList("Branch");
	 int userIn = scanner();
	 switch(userIn) {
	 case 1:
		 System.out.println("");
		 branchAdd();
		 break;
	 case 2:
		 System.out.println("");
		 branchUpdate();
		 break;
	 case 3:
		 System.out.println("");
		 branchDelete();
		 break;
	 case 4:
		 System.out.println("");
		 branchRead();
		 break;
	 case 5:
		 System.out.println("");
		 adminMenu();
		 break;
	 }
 }
 
 
 public void branchAdd() {
	 LibrarianService librarianService = new LibrarianService();
	 System.out.println("Enter the name for the new branch");
	 String branchName = scannerS();
	 System.out.println("Enter an address for the new branch");
	 String branchAddress = scannerS();
	 Branch branch = new Branch(null, branchName, branchAddress );
	 librarianService.addBranch(branch);
	 //branch.setBranchAddress(branchAddress);
	 librarianService.updateBranchAddress(branch);
	 adminMenu();
	 
 }
 
 public void branchUpdate() {
	 LibrarianUi librarianUi = new LibrarianUi();
	 Branch branch = pickBranch();
	 librarianUi.updateLibrary2(branch);
	 adminMenu();
	 
 }
 
 public void branchDelete() {
	 LibrarianService librarianService = new LibrarianService();
	 Branch branch = pickBranch();
	 librarianService.deleteBranch(branch);
	 adminMenu();
 }
 
 public void branchRead() {
	 LibrarianService librarianService = new LibrarianService();
	 List<Branch> bList =librarianService.getBranches(null);
	 for(Branch branch : bList) {
		 System.out.println(branch.getBranchName()+", "+branch.getBranchAddress());
	 }
	 System.out.println("Enter anything to return to adminMenu");
	 String s = scannerS();
	 adminMenu();
 }
 
 
 public Branch pickBranch() {
	 LibrarianService librarianService = new LibrarianService();
	 System.out.println("Please select a branch.");
	 List<Branch> bList =librarianService.getBranches(null);
	 int i =0;
	 for(Branch branch : bList) {
		 i++;
		 System.out.println(i+") "+branch.getBranchName()+", "+branch.getBranchAddress());
	 }
	 
		System.out.println((i+1)+") Quit to previous");
	   int userIn = scanner();
			
			if(userIn==i+1) {
				System.out.println("");
				adminMenu();
				System.exit(0);
			}
			
			Branch tBranch= bList.get(userIn-1);
			return tBranch;
 }
 
 
 public void editBorrowers() {
	 actionList("Borrower");
	 int userIn = scanner();
	 switch(userIn) {
	 case 1:
		 System.out.println("");
		 borrowerAdd();
		 break;
	 case 2:
		 System.out.println("");
		 borrowerUpdate();
		 break;
	 case 3:
		 System.out.println("");
		 borrowerDelete();
		 break;
	 case 4:
		 System.out.println("");
		 borrowerRead();
		 break;
	 case 5:
		 System.out.println("");
		 adminMenu();
		 break;
	 }
 }
 
 public void borrowerAdd() {
	 AdministratorService adminService = new AdministratorService();
	 System.out.println("Please enter the name");
	 String name = scannerS();
	 System.out.println("Please enter the Address");
	 String address = scannerS();
	 System.out.println("Please enter the Phone number");
	 String phone = scannerS();
	 Borrower borrower = new Borrower(null, name, address, phone);
	 adminService.addBorrower(borrower);
	 adminMenu();
	 
 }
 
 public void borrowerUpdate() {
	 AdministratorService adminService = new AdministratorService();
	 Borrower borrower = pickBorrower();
	 System.out.println("What do you wish to update about"+borrower.getCardNo()+", "+borrower.getName()+", "+borrower.getAddress()+", "+borrower.getPhone()+"?");
	 System.out.println("1) Name");
	 System.out.println("2) Address");
	 System.out.println("3) Phone");
	 System.out.println("4) Quit to Admin");
	 int userIn = scanner();
	 String s = "placeholder";
	 switch(userIn) {
	 case 1:
		 System.out.println("Enter new Name (Formally "+borrower.getName()+")");
		 s = scannerS();
		 borrower.setName(s);
		 break;
	 case 2:
		 System.out.println("Enter new Address (Formally "+borrower.getAddress()+")");
		 s = scannerS();
		 borrower.setAddress(s);
		 break;
	 case 3:
		 System.out.println("Enter new Phone number (Formally "+borrower.getPhone()+")");
		 s = scannerS();
		 borrower.setPhone(s);
		 break;
	 case 4:
		 adminMenu();
		 System.exit(0);
	 }
	 
	 adminService.updateBorrower(borrower);
	 adminMenu();
 }
 
 public void borrowerDelete() {
	 AdministratorService adminService = new AdministratorService();
	 Borrower borrower = pickBorrower();
	 adminService.deleteBorrower(borrower); 
	 adminMenu();
 }
 
 
 public void borrowerRead() {
	 AdministratorService adminService = new AdministratorService();
	 List <Borrower> bList = adminService.readAllBorrowers();
	 for(Borrower borrower : bList) {
		 System.out.println(borrower.getCardNo()+", "+borrower.getName()+", "+borrower.getAddress()+", "+borrower.getPhone());
	 }
	 System.out.println("Enter any char to continue");
	 String s = scannerS();
	 adminMenu();
 }
 
 public Borrower pickBorrower() {
	 System.out.println("Select a borrower");
	 AdministratorService adminService = new AdministratorService();
	 List <Borrower> bList = adminService.readAllBorrowers();
	 int i =0;
	 for(Borrower borrower : bList) {
		 i++;
		 System.out.println(i+") "+borrower.getCardNo()+", "+borrower.getName()+", "+borrower.getAddress()+", "+borrower.getPhone());
	 }
		System.out.println((i+1)+") Quit to previous");
		   int userIn = scanner();
				
				if(userIn==i+1) {
					System.out.println("");
					adminMenu();
					System.exit(0);
				}
				
				Borrower tBorrower= bList.get(userIn-1);
				return tBorrower;
 }
 
 
 public void loansReadAll() {
	 BorrowerService bS = new BorrowerService();
		List<Loans> lList =bS.getAllLoans();
		for(Loans aLoan : lList) {
			System.out.println("Loan: bookId "+ aLoan.getBookId()+ " branchId "+aLoan.getBranchId()+" cardNo "+aLoan.getCardNo());
			System.out.println("      Out "+ aLoan.getDateOut()+ " Due "+aLoan.getDueDate()+" In "+aLoan.getDateIn());
		}
		 System.out.println("Enter any char to continue");
		 String s = scannerS();
		 adminMenu();
 }
 
 
 
 
 public void overrideLoan() {
	 BorrowerService bS = new BorrowerService();
	 	
		Loans loan = pickLoan();
//		 Calendar cal = Calendar.getInstance();
//		 cal.set(9999, 8, 9);
//		 Date overTime = new Date(cal.getTimeInMillis());
//		 System.out.println("Time is : "+overTime);
//		 loan.setDueDate(overTime);
//		 bS.updateLoanOverride(loan);
		System.out.println("Set a new time in format 'yyyy-mm-dd' ");
	 	Date overRide = scannerD();
		 loan.setDueDate(overRide);
		 bS.updateLoanOverride(loan);
		 adminMenu();
		 
	}
 
 
 public Loans pickLoan() {
	 System.out.println("Select a loan");
	 BorrowerService bS = new BorrowerService();
		List<Loans> lList =bS.getAllLoans();
		int i = 0;
		for(Loans aLoan : lList) {
			i++;
			System.out.println(i+") Loan: bookId: "+ aLoan.getBookId()+ " branchId: "+aLoan.getBranchId()+" cardNo: "+aLoan.getCardNo());
			System.out.println("      Out: "+ aLoan.getDateOut()+ " Due: "+aLoan.getDueDate()+" In: "+aLoan.getDateIn());
		}
	 
		System.out.println((i+1)+") Quit to previous");
		   int userIn = scanner();
				
				if(userIn==i+1) {
					System.out.println("");
					adminMenu();
					System.exit(0);
				}
				
				Loans loan= lList.get(userIn-1);
				return loan;
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
	
	
	public Date scannerD() {
		Scanner sc = new Scanner(System.in); 
		 Calendar cal = Calendar.getInstance();
		 cal.set(9999, 8, 9);
		 Date overRide = new Date(cal.getTimeInMillis());
		 
		String userIn = "1111-11-11";
		try {
			userIn= sc.nextLine();	
			overRide = java.sql.Date.valueOf(userIn);
		}
		catch(InputMismatchException exception)
		{
			System.out.println("That was not a valid input");
			sc.next();
		}
		System.out.println("Override Time is : "+overRide);
		return overRide;
	}
	
	public List<Author> authorSelector() {
		AdministratorService adminService = new AdministratorService();
		List<Author>bList = new ArrayList<>();
		List<Author> aList = adminService.getAuthors(null);
		int userIn = 0;
		int i =0;
		int again =1;
	do {
		i=0;
		System.out.println("Please select an Author");
		for(Author aAuthor :aList) {
			i++;
			System.out.println(i+") "+aAuthor.getAuthorName());
		}
		System.out.println((i+1)+") Quit to previous");
    userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
			System.exit(0);
		}
		
		Author tAuthor= aList.get(userIn-1);
		bList.add(tAuthor);
		aList.remove(userIn-1);
		System.out.println("You have selected "+tAuthor.getAuthorName());
		System.out.println("Would you like to select another author?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		again = scanner();
		}
		while(again == 1);
		return bList;
	}
	
	public List<Genre> genreSelector() {
		AdministratorService adminService = new AdministratorService();
		List<Genre>bList = new ArrayList<>();
		List<Genre> aList = adminService.getGenres();
		int userIn = 0;
		int i =0;
		int again =1;
	do {
		i=0;
		System.out.println("Please select a Genre");
		for(Genre aGenre :aList) {
			i++;
			System.out.println(i+") "+aGenre.getGenreName());
		}
		System.out.println((i+1)+") Quit to previous");
    userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
			System.exit(0);
		}
		
		Genre tGenre= aList.get(userIn-1);
		bList.add(tGenre);
		aList.remove(userIn-1);
		System.out.println("You have selected "+tGenre.getGenreName());
		System.out.println("Would you like to select another Genre?");
		System.out.println("1) Yes");
		System.out.println("2) No");
		again = scanner();
		}
		while(again == 1);
		return bList;
	}
	
	
	
	public Publisher publisherSelector() {
		System.out.println("Please select a Publisher.");
		AdministratorService adminService = new AdministratorService();
		
		List<Publisher> pList = adminService.getPublishers(null);
		int i =0;
		for(Publisher aPublisher :pList) {
			i++;
			System.out.println(i+") "+aPublisher.getPublisherName()+", "+ aPublisher.getPublisherAddress());
		}
		System.out.println((i+1)+") Quit to previous");
int userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
			System.exit(0);
		}
		return pList.get(userIn-1);
	}
	
	
	
	public Author authorSelector2() {
		AdministratorService adminService = new AdministratorService();
		List<Author>bList = new ArrayList<>();
		List<Author> aList = adminService.getAuthors(null);
		int userIn = 0;
		int i =0;
		int again =1;
	
		i=0;
		System.out.println("Please select an Author");
		for(Author aAuthor :aList) {
			i++;
			System.out.println(i+") "+aAuthor.getAuthorName());
		}
		System.out.println((i+1)+") Quit to previous");
    userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
			System.exit(0);
		}
		
		Author tAuthor= aList.get(userIn-1);
		return tAuthor;
	}
	
	
	public Genre genreSelector2() {
		AdministratorService adminService = new AdministratorService();
		
		List<Genre> aList = adminService.getGenres();
		int userIn = 0;
		int i =0;
	
	
		System.out.println("Please select a Genre");
		for(Genre aGenre :aList) {
			i++;
			System.out.println(i+") "+aGenre.getGenreName());
		}
		System.out.println((i+1)+") Quit to previous");
    userIn = scanner();
		
		if(userIn==i+1) {
			System.out.println("");
			adminMenu();
			System.exit(0);
		}
		
		Genre tGenre= aList.get(userIn-1);
	
	
		return tGenre;
	}
	
	
	public Book pickABook() {
		 AdministratorService adminService = new AdministratorService();
			List<Book> bList = adminService.getBooks(null);
			int i =0;
			for(Book aBook :bList) {
				i++;
				List<String> aNames = new ArrayList<>();
				List <Author> aList = aBook.getAuthors();
			 	for(Author author : aList) {
			 		aNames.add(author.getAuthorName());
			 	}
			 	String authorString = String.join(", ", aNames);
				System.out.println(i+") "+aBook.getTitle()+", Author(s): "+ authorString);
			}
			System.out.println((i+1)+") Quit to previous");

			int userIn = scanner();
			
			if(userIn==i+1) {
				System.out.println("");
				adminMenu();
				System.exit(0);
			}
			Book selectedBook = bList.get(userIn-1);
			return selectedBook;
	}
	
	
	
	public int optionSet1(String thing) {
		actionList(thing);
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
			return 1;
		case 2:
			System.out.println("");
			return 2;
		case 3:
			System.out.println("");
			return 3;
		case 4:
			System.out.println("");
			return 4;
		case 5:
			System.out.println("");
			adminMenu();
			break;
			default:
				System.out.println("Error with selection");
				return 0;
		}
		return 0;
	}

	
	
}
