import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

	//count the common moves
	static int rCount;
	static int pCount;
	static int sCount;

	public static void main(String[] args) {

		//cause shutdown is needed to end console program
		boolean shutdown = false;

		//scan for user input
		Scanner scanMenu = new Scanner(System.in);

		//prints instructions
		menu();

		//while shutdown is false. in a try catch to stop string inputs. kills the program
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
				System.out.println("Do not enter letters, launch again");
				return;
			}
		}
	}
	
	//prints a menu for the user. '\n' for new line.
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

		//scan human inputs for human vs human
		Scanner scanHuman = new Scanner(System.in);

		//used as a check for when determining a winner
		boolean winner = false;

		//start counting how many games are played
		int gameCount = 0;
		
		//declare counts of move played to 0. (new game)
		rCount = 0;
		pCount = 0;
		sCount = 0;



		while(!winner) {

			//game count goes from 0 to 1. 
			gameCount++;
			
			//reset the inputs
			char playerOne = 'a';
			char playerTwo = 'b';

			//check if the input is valid, if not, scan in! keep doing this till happy.
			while(!inputCheck(playerOne)){
				System.out.println("Player 1: ");
				playerOne = scanHuman.next(".").charAt(0);
			}

			//same with player 2 
			while(!inputCheck(playerTwo)){
				System.out.println("Player 2: ");
				playerTwo = scanHuman.next(".").charAt(0);
			}


			//check rules to see if player one wins
			if(rules(playerOne, playerTwo)) {
				System.out.println("Player One wins. This game took " + gameCount + " attempts!");
				commonMovePrinter();
				winner = true;
				
			//check to see if player 2 wins	
			} else if (rules(playerTwo, playerOne)) {
				System.out.println("Player Two wins. This game took " + gameCount + " attempts!");
				commonMovePrinter();
				winner = true;
				
			//its a draw otherwise
			} else {
				System.out.println("Draw! try again");
			}
		}
	}

	//victory conditions are stored here
	public static boolean rules(char inputOne, char inputTwo) {

		//rock beats scissors, scissors beat paper and paper beats rock
		if(inputOne == 'r' && inputTwo == 's' ||
				inputOne == 's' && inputTwo == 'p' ||
				inputOne == 'p' && inputTwo == 'r'){
			return true;
		}
		return false;
	}

	//makes sure user input is valid. also increase count of move played.
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

	//prints the most common move with a series of ifs. would like this to be a toString or a loop. future work!
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

	//human Vs. AI. same style
	public static void AI() {

		//character choices for the AI
		char[] rps = {'r','p','s'};

		Scanner scanHuman = new Scanner(System.in);

		boolean winner = false;

		int gameCount = 0;

		//AI just randomly chooses. no behavioural system in place.
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

			//still go through input check as the counter for move played is there.
			playerAI = rps[rng.nextInt(3)];
			inputCheck(playerAI);
			//print AI move, in case of confusion
			System.out.println("The AI chose : " + playerAI);


			//same rule system. must be moved to another method to remove redundant code. OOP principles. future work.
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
