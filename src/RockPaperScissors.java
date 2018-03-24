import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
	
	static int rCount;
	static int pCount;
	static int sCount;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean shutdown = false;
		
		Scanner scanMenu = new Scanner(System.in);
		
		menu();

		while(!shutdown) {
			try {
			int input = scanMenu.nextInt();		
			if (input == 1) {
				System.out.println("Loading Human vs Human");
				Human();
			} else if (input == 2) {
				System.out.println("Loading Human vs AI");
				AI();
			} else if (input == 3) {
				System.out.println("Shutting Down");
				shutdown = true;
			} else {
				System.out.println("Not a valid Entry");
			}
		} 
		catch (InputMismatchException e) {
			System.out.println("Do not enter a letter, launch again");
			return;
		}
	}
	}
	public static void menu() {
		System.out.println("Welcome to Rock Paper Scissors \n"
				+ "Please enter a number to navigate the menu \n"
				+ "1: Play against a human\n"
				+ "2: Play against an AI \n"
				+ "3: Exit the game\n\n"
				+ "How to Play: \n"
				+ "Enter 'r' for Rock\n"
				+ "Enter 'p' for Paper\n"
				+ "Enter 's' for Scissors");
	}
	
	public static void Human() {
		
		Scanner scanHuman = new Scanner(System.in);
		
		boolean winner = false;
		
		int gameCount = 0;
		rCount = 0;
		pCount = 0;
		sCount = 0;
		
		
		
		while(!winner) {
			
			gameCount++;
			char playerOne = 'a';
			char playerTwo = 'b';
			
			while(!inputCheck(playerOne)){
				System.out.println("Player 1: ");
				playerOne = scanHuman.next(".").charAt(0);
			}
		
			while(!inputCheck(playerTwo)){
				System.out.println("Player 2: ");
				playerTwo = scanHuman.next(".").charAt(0);
			}
			
			
			if(rules(playerOne, playerTwo)) {
				System.out.println("Player One wins. This game took " + gameCount + " attempts!");
				commonMovePrinter();
				winner = true;
			} else if (rules(playerTwo, playerOne)) {
				System.out.println("Player Two wins. This game took " + gameCount + " attempts!");
				commonMovePrinter();
				winner = true;
			} else {
				System.out.println("Draw! try again");
			}
		}
	}
	
	public static boolean rules(char inputOne, char inputTwo) {
		
		//player one victory conditions
		if(inputOne == 'r' && inputTwo == 's' ||
				inputOne == 's' && inputTwo == 'p' ||
				inputOne == 'p' && inputTwo == 'r'){
			return true;
		}
		return false;
	}
	
	public static boolean inputCheck(char input) {
		if(input == 'r') {
			rCount++;
			return true;
		} else if (input == 'p') {
			pCount++;
			return true;
		} else if (input == 's') {
			sCount++;
			return true;
		}
		return false;
	}
	
	public static void commonMovePrinter() {
		if(rCount > pCount && rCount > sCount) {
			System.out.println("Rock was the most common move");
		} else if (pCount > rCount && pCount > sCount) {
			System.out.println("Paper was the most common move");
		} else if (rCount == pCount) {
			System.out.println("Both Rock and Paper was the most common move");
		} else  if (rCount == sCount ) {
			System.out.println("Both Rock and Scissors was the most common move");
		} else if (pCount == sCount) {
			System.out.println("Both Paper and Scissors was the most common move");
		} else {
			System.out.println("Scissors was the most common move");
		}
	}
	
	public static void AI() {
		
		
		char[] rps = {'r','p','s'};
		
		Scanner scanHuman = new Scanner(System.in);
		
		boolean winner = false;
		
		int gameCount = 0;
		
		Random rng = new Random();
		
		
		rCount = 0;
		pCount = 0;
		sCount = 0;
		
		
		while(!winner) {
			
			gameCount++;
			char playerOne = 'a';
			char playerAI = 'b';
		
		while(!inputCheck(playerOne)){
			System.out.println("Player 1: ");
			playerOne = scanHuman.next(".").charAt(0);
		}
		
		playerAI = rps[rng.nextInt(3)];
		inputCheck(playerAI);
		System.out.println("The AI chose : " + playerAI);
		
		
		if(rules(playerOne, playerAI)) {
			System.out.println("Player One wins. This game took " + gameCount + " attempts!");
			commonMovePrinter();
			winner = true;
		} else if (rules(playerAI, playerOne)) {
			System.out.println("Player Two wins. This game took " + gameCount + " attempts!");
			commonMovePrinter();
			winner = true;
		} else {
			System.out.println("Draw! try again");
		}		
	}
	}
}
