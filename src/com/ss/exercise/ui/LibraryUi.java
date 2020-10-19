/**
 * 
 */
package com.ss.exercise.ui;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Ian
 *
 */
public class LibraryUi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the SS Library Management System. Which category of a user are you");
		System.out.println("1)Librarian");
		System.out.println("2)Administrator");
		System.out.println("3)Borrower");
		
		Scanner sc = new Scanner(System.in); 
		int userIn = 0;
		do {
			
			try {
				
				userIn= sc.nextInt();
				if(userIn>3 || userIn<1) {
					System.out.println("*please enter a number in range");
				}
				
			}
			catch(InputMismatchException exception)
			{
				System.out.println("That was not a valid input");
				sc.next();
			}
		}
		while(userIn>3 || userIn<0);
		
		switch (userIn) {
		case 1:
			System.out.println("");
			LibrarianUi librarianUi = new LibrarianUi();
			librarianUi.librarianMenu();
			break;
		case 2:
			System.out.println("");
			AdminUi adminUi = new AdminUi();
			adminUi.adminMenu();
			break;
		case 3:
			System.out.println("Borrow");
			BorrowerUi borrowerUi = new BorrowerUi();
			borrowerUi.borrowerMenu();
			break;
		default:
			System.out.println("Error with selection");
		}

	}

}
