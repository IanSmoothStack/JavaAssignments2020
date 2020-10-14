import java.util.InputMismatchException;
import java.util.Scanner;

public class OddChips {
	
	public void gameStart() {
		boolean playGame = true;
		do {
			Scanner sc = new Scanner(System.in); 
		
			char playAgain = 'l';
			boolean namesSame = true;
			String player1Name;
			String player2Name;
			System.out.println("What is the name of the first player?");
			player1Name= sc.next();
			do {
				
					System.out.println("What is the name of the second player?");					
					player2Name= sc.next();
				
				if(player1Name.equalsIgnoreCase(player2Name)) {
					System.out.println("*Players name can not be the same.");
				}
				else {
					namesSame=false;
				}
					
				
				
			}
			while(namesSame);
			
			
			//System.out.println("Players names are "+ player1Name + " and " +player2Name);
			
			System.out.println("");
			
			///Players select starting chip pile size
			int chipPile = 0;
			
			do {
				
				try {
					System.out.println("");
					System.out.println("Enter how many chips you want to start with. (Has to be an odd number greater than three.)");
					//System.out.println("Has to be an odd number greater then three");
					chipPile= sc.nextInt();
					if(chipPile<3) {
						System.out.println("*There needs to be at least three chips to start with");
					}
					if(chipPile % 2 == 0) {
						System.out.println("*There needs to be an odd number of chips");
					}
				}
				catch(InputMismatchException exception)
				{
					System.out.println("That was not a valid answer");
					sc.next();
				}
			}
			while(chipPile<3 ||(chipPile % 2 == 0));
			
			System.out.println("");
			System.out.println("**********Game Start**************");
			System.out.println("");
			
			int p1Chips = 0;
			int p2Chips = 0;
			int turn=0;
			int chipChange;
			while(chipPile>0) {
				turn++;
				int pTurn = turn % 2;
				System.out.println("****** Turn "+turn+" *******");
				System.out.println(player1Name+" has "+p1Chips+" chips.");
				System.out.println(player2Name+" has "+p2Chips+" chips.");
				System.out.println("");
				switch(pTurn) {
				case 0:
					System.out.println("It's " + player2Name +" turn.");
					 chipChange= playersTurn(chipPile, player2Name);
					 chipPile= chipPile-chipChange;
					 p2Chips = p2Chips+chipChange;
					break;
				case 1: 
					System.out.println("It's " + player1Name +" turn.");
					 chipChange = playersTurn(chipPile, player1Name);
					 chipPile= chipPile-chipChange;
					 p1Chips = p1Chips+chipChange;
					break;
				default:
					System.out.println("Error with turns");
				
				}
				
				
				
			}
			
			
			System.out.println(player1Name+" has "+p1Chips+" chips.");
			System.out.println(player2Name+" has "+p2Chips+" chips.");
			if(p1Chips%2==0 && p2Chips%2==1) {
				System.out.println("*************");
				System.out.println(player1Name+" Wins!");
				System.out.println("*************");
			}
			else if(p1Chips%2==1 && p2Chips%2==0) {
				System.out.println("*************");
				System.out.println(player2Name+" Wins!");
				System.out.println("*************");
			}
			else {
				System.out.println("Something went wrong");
			}
			
			
			
			
			////This part lets the players decide if they want to play the game again
			do {
				try {
					System.out.println("Play another game? (y/n)");
					playAgain = sc.next().charAt(0);
					
				}
				catch(InputMismatchException exception)
				{
					System.out.println("That was not a valid answer");
					sc.next();
				}
				}
				while(playAgain != 'y' && playAgain !='n' && playAgain != 'Y' && playAgain !='N');
			
			switch(playAgain) {
				case 'y':
					playGame= true;
					System.out.println("You have selected to play again.");
					break;
				case 'Y':
					playGame= true;
					System.out.println("You have selected to play again.");
					break;
				case 'n':
					playGame = false;
					System.out.println("You have opted to not play again.");
					break;
				case 'N':
					playGame = false;
					System.out.println("You have opted to not play again.");
					break;
				default:
						System.out.println("You did not enter a valid response");
			}
		}
		while(playGame);
	}
	
	public int playersTurn(int chipPile, String playerName) {
		Scanner sc = new Scanner(System.in); 
		int max = (chipPile-1)/2 ;
		if(chipPile%2 == 0)
			max++;
		if(max<1)
			max=1;
		int chipTake = 0;
		
		
		do {
			try {
			
				System.out.println("There are "+ chipPile +" total chips remaining in the pile");
				System.out.println("You may take any number of chips from 1 to "+ max +". How many chips would you like to take "+playerName+"?");
				chipTake = sc.nextInt();
				if(chipTake>max || chipTake<1) {
					System.out.println("*You have entered a number outside of the accepted range");
				}
			}
			catch(InputMismatchException exception)
			{
				System.out.println("That was not a valid answer");
				sc.next();
			}
		}
		while(chipTake>max || chipTake<1);
		
		return chipTake;
	}

}
