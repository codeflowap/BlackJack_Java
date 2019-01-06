import java.lang.Math;
import java.lang.String;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class blackJack {

	public static void main(String[] args) {
		
		startGame();
		
	}
	
	public static void startGame() {
		String userin = "tt";
		String ustr = "";
		String aistr = "";
		String s1 = "";
		String[] s = {"",""};
		int currentscore, score, uservalue, loop, checkwin, aiscore, airisk; 
		
		// AI score is calculated based on the this array
		// final int[] aiarray = {65,2,3,4,5,6,7,8,9,10,74,81,75};
		
		currentscore = 0;
		uservalue = 0;
		aiscore = 0;
		airisk = 16;
		
		// user input array - length change step by step
		int[] u;
		

		System.out.println("Game start:");
		
		// loop to asses if the user has gone over 21 or not
		checkwin = 1;
		while (checkwin ==1) {
			
			int firstinput = 1;
			
			//Check if the user input matches [2-10], A, J, K, Q
			while (firstinput ==1) {
				System.out.print("You pick a card: ");
				Scanner input = new Scanner(System.in); 		
				userin = input.next();
	
				if (userin.equals("A")) {
					uservalue = 1;
					ustr = ustr.concat(userin + " ");
					firstinput = 0;
				} else if (userin.equals("J") || userin.equals("K") || userin.equals("Q") ) {
					uservalue = 10;
					ustr = ustr.concat(userin + " ");
					firstinput = 0;
				} else {
					int userinint = Integer.parseInt(userin);
					if (userinint>=2 && userinint<=10) {
						uservalue = userinint;
						ustr = ustr.concat(userin + " ");
						firstinput = 0;
					}
					else {
							System.out.println("Your input is not in range [1-10]. Try again!");
					}
				}
			}
			
			// Stores first inputs from the user
			currentscore = currentscore + uservalue;	
			System.out.println("Your score is: " + currentscore);

		// loops until the user enters f from [t/f] option
		loop = 1;
		while (loop == 1) {
			
			if (currentscore >= 22) {
				System.out.println("Your lose");
				checkwin = 0;
				break;
			} else if ( currentscore == 21) {
				aiTurn(currentscore);
				checkwin = 0;
				break;
			}
			else {
				System.out.print("Would you like to take another card [t/f]? ");
				Scanner uans = new Scanner(System.in);
				String answer = uans.next();
				
				if (answer.equals("t") | answer.equals("T")) {
					s = pickACard();
					ustr = ustr.concat(s[0] + " ");
					System.out.println("Your current cards are: " + ustr);
					currentscore = currentscore + Integer.parseInt(s[1]);
					System.out.println("Your score is: " + currentscore);
				}
				else if (answer.equals("f") | answer.equals("F")) {
						loop = 0;	

						// AI starts
						aiTurn(currentscore);
						checkwin = 0;
						break;		
				}
				else {
					System.out.print("You did not enter [t/f] sorry. I terminate it.");
					System.out.println("");
					checkwin = 0;
					break;
				}
				
			}
			} // while loop
		} // while check win
	} 

	
	// A method for generating random numbers between min and max
	public static int genRanInt(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
			
		
	// AI turn method - it calls another method: pickACard 
	// This method loops until the calculated score is compared with the user's 
	public static void aiTurn(int usc) {
		
		int aiscore = 0;
		String s1 = "";
		String aistr = "";
		String[] s = {"",""};
		
		System.out.println("-----------------------");
		System.out.println("AI turn");
	
		s = pickACard();
		System.out.print("AI pick a card: "); 
		System.out.println(s[0]);
		
		// s[1] is the AI score which is converted to integer 
		int accum = Integer.parseInt(s[1]);
		aiscore = aiscore + accum;
		//s[0] is the card name as string
		aistr = aistr.concat(s[0] + " ");
		System.out.println("AI score is: " + aiscore);
		
		while (aiscore <= usc) {

			s = pickACard();
			accum = Integer.parseInt(s[1]);
			aistr = aistr.concat(s[0] + " ");
			aiscore = aiscore + accum;
			
			System.out.println("AI cards are: " + aistr);
			System.out.println("AI score is: " + aiscore);
		 }

		// compare the results 
		if (aiscore > usc && aiscore < 22) {
			System.out.println("AI win");
		} else if (aiscore > usc && aiscore >= 22) {
			System.out.println("You win");
		} else if (aiscore < usc) {
			System.out.println("You win");
		} else if (aiscore == usc && usc == 21) {
			System.out.println("The game is draw!");
		}
	
	}
			
	
	// A method that picks a card for user or for AI and has an String array output:
	// s[0] gives the card name
	//s[1] gives the score calculated after the card is selected 
	// This methid does not loop and is run ONCE
	public static String[] pickACard () {
	
		String s1 = "";
		String s2 = "";
		String[] s= {"",""};
		int sscs = 0;
		int airand = genRanInt(0,13);
		
		// Calculates the score based on values in this array:
		// {65, 2, 3, 4, 5, 6, 7, 8, 9, 10, 74, 81, 75} A=65, J=74, K=75, Q=81
		//  0   1  2  3  4  5  6  7  8  9   10  11  12
		if (airand >= 1 && airand <= 9) {
			sscs = sscs + airand + 1 ;
			s1 = Integer.toString(airand+1);
		} 
		else if (airand == 11 || airand == 12 || airand == 13) {
			sscs = sscs + 10;
			if (airand == 11) {
				s1 = "J";
			}
			else if (airand == 12) {
				s1 = "Q";
			}
			else if (airand == 13) {
				s1 = "K";
			}
		} 
		else if (airand == 0) {
			sscs = sscs + 1;
			s1 = "A";
		}
		
		s[0] = s1;
		s[1] = Integer.toString(sscs);
		
		return s;
	}
	
}
















