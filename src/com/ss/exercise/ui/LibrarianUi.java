/**
 * 
 */
package com.ss.exercise.ui;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ss.exercise.dao.BranchDAO;
import com.ss.exercise.entity.Book;
import com.ss.exercise.entity.Branch;
import com.ss.exercise.entity.NumberOfCopies;
import com.ss.exercise.service.AdministratorService;
import com.ss.exercise.service.LibrarianService;

import java.util.List;

/**
 * @author Ian
 *
 */
public class LibrarianUi {
	
	///Lib1
	public void librarianMenu(){
		System.out.println("What would you like to do?");
		System.out.println("1) Enter your branch number");
		System.out.println("2) Quit to previous");
		
		
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
			pickBranches();
			break;
		case 2:
			System.out.println("");
			LibraryUi libraryUi = new LibraryUi();
			libraryUi.main(null);
			break;
	
		default:
				System.out.println("Error with selection");
		}
	}
	
	
	//Lib2
	public void pickBranches() {
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
			librarianMenu();
			return;
		}
		Branch userBranch = bList.get(userIn-1);
		System.out.println("You have selected the "+ userBranch.getBranchName()+ " branch.");
		branchOptions(userBranch);
		///return here?
		
	}
	
	///Lib3
	public void branchOptions(Branch userBranch) {
		System.out.println("You are currently in the "+ userBranch.getBranchName()+" branch.");
		System.out.println("Please select an option.");
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Add Copies of a Book to the Branch");
		System.out.println("3) Quit to previous.");
		int userIn = scanner();
//		if(userIn==3) 
//			pickBranches();
		switch (userIn) {
		case 1:
			System.out.println("");
			updateLibrary(userBranch);
			break;
		case 2:
			System.out.println("");
			updateBooksInBranch(userBranch);
			break;
		case 3:
			pickBranches();
			break;
	
		default:
				System.out.println("Error with selection");
		}
			
		
	}
	
	public void updateBooksInBranch(Branch userBranch) {
		System.out.println("Please select the book you wish to update.");
		AdministratorService adminService = new AdministratorService();
		LibrarianService librarianService = new LibrarianService();
		AdminUi adminUi = new AdminUi();

		Book selectedBook = adminUi.pickABook();
		int bookId=selectedBook.getBookId();
		int branchId = userBranch.getBranchId();
		System.out.println("You have selected the book : "+ selectedBook.getTitle()+ ".");
		List<NumberOfCopies> noc =librarianService.getNumberOfCopies(bookId, branchId);
		int numCshow=0;
		NumberOfCopies numC;
		if(!noc.isEmpty()) {
			 numC =noc.get(0);
			numCshow = numC.getNumOfCopies();	
		}
		System.out.println("The "+userBranch.getBranchName()+" branch currently have "+numCshow+" number of copies of the book "+selectedBook.getTitle()+".");
		System.out.println("Please enter the new number of copies");
		int userIn = scanner();
		 if(!noc.isEmpty()) {
			 librarianService.updateNumOfCopies(bookId, branchId, userIn);
		 }
		 else {
			 
			 librarianService.addCopies(bookId, branchId, userIn);
		 }
		branchOptions(userBranch);
		///return here?
	}
	
	
	public void updateLibrary(Branch userBranch) {
		LibrarianService librarianService = new LibrarianService();
		System.out.println("You have chosen to update the Branch with Branch Id: "+userBranch.getBranchId()+
				" and Branch Name: "+userBranch.getBranchName()+". Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new branch name or enter N/A for no change:");
		
		String userIn = scannerS();
		if(userIn.equalsIgnoreCase("quit")) {
			branchOptions(userBranch);
			return;
		}
		
		if(userIn.equalsIgnoreCase("N/A")||userIn.equalsIgnoreCase(""))
			System.out.println("No change");
		else {
			userBranch.setBranchName(userIn);
			librarianService.updateBranchName(userBranch);
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		userIn = scannerS();
		if(userIn.equalsIgnoreCase("quit")) {
			branchOptions(userBranch);
			return;
		}
		
		if(userIn.equalsIgnoreCase("N/A")||userIn.equalsIgnoreCase(""))
			System.out.println("No change");
		else {
			userBranch.setBranchAddress(userIn);
			librarianService.updateBranchAddress(userBranch);
		}
		
		
		System.out.println("Succefully updated");
		branchOptions(userBranch);
		///return?
	}
	
	
	
	public void updateLibrary2(Branch userBranch) {
		LibrarianService librarianService = new LibrarianService();
		System.out.println("You have chosen to update the Branch with Branch Id: "+userBranch.getBranchId()+
				" and Branch Name: "+userBranch.getBranchName()+". Enter ‘quit’ at any prompt to cancel operation.");
		System.out.println("Please enter new branch name or enter N/A for no change:");
		
		String userIn = scannerS();
		if(userIn.equalsIgnoreCase("quit")) {
			branchOptions(userBranch);
			return;
		}
		
		if(userIn.equalsIgnoreCase("N/A")||userIn.equalsIgnoreCase(""))
			System.out.println("No change");
		else {
			userBranch.setBranchName(userIn);
			librarianService.updateBranchName(userBranch);
		}
		System.out.println("Please enter new branch address or enter N/A for no change:");
		userIn = scannerS();
		if(userIn.equalsIgnoreCase("quit")) {
			branchOptions(userBranch);
			return;
		}
		
		if(userIn.equalsIgnoreCase("N/A")||userIn.equalsIgnoreCase(""))
			System.out.println("No change");
		else {
			userBranch.setBranchAddress(userIn);
			librarianService.updateBranchAddress(userBranch);
		}
		
		
		System.out.println("Succefully updated");
		
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
