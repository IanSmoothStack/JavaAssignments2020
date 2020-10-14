import java.util.Scanner;

public class MainRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Hello World.");
		Scanner sc = new Scanner(System.in); 
		System.out.println("Enter the number of which program you wish to start");
		System.out.println("1) GuessNum");
		System.out.println("2) OddChips");
		int programStart = sc.nextInt();
		switch(programStart) {
		case 1:
		GuessNum guessNum = new GuessNum();
		guessNum.gameStart();
		break;
		case 2:
		OddChips oddChips = new OddChips();
		oddChips.gameStart();
		break;
		default:
			System.out.println("You selected an invalid choice");
			break;
		}
	}

}
