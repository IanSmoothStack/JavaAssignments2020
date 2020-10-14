import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class GuessNum {
	
	public  void gameStart() {
		Random rand = new Random();
		 Scanner sc = new Scanner(System.in); 
		int int_random = rand.nextInt(100)+1;
		int guessLeft = 5;
		//System.out.println(int_random);
		System.out.println("This is a game where you need to guess the number which can be between 1-100");
		System.out.println("If your guess is whithin 10 numbers of the answer then you will be told the number");
		System.out.println("You will be given 5 chances to guess.");
		while (guessLeft>0) {
			boolean isInt = false;
			int playerGuess = -1;
			//System.out.println("Enter your guess (please only enter whole numbers from 1-100)");
			do {
			try {
				System.out.println("Enter your guess (please only enter whole numbers from 1-100)");
				 playerGuess = sc.nextInt();
				isInt = true;
			}
			catch(InputMismatchException exception)
			{
				System.out.println("That was not an interger");
				sc.next();
			}
			}
			while(!isInt);
			if(Math.abs(playerGuess-int_random)<=10) {
				System.out.println("You did it!");
				System.out.println("The correct number was "+int_random);
				System.out.println("Your guess was within "+Math.abs(playerGuess-int_random)+ " number(s) of the answer");
				System.exit(0);
			}
			else {
				System.out.println("Incorrect");
				guessLeft--;
				System.out.println("You have "+guessLeft+" guesses remaining");
			}
			
		}
		System.out.println("Sorry");
		System.exit(0);
		
	}

}
