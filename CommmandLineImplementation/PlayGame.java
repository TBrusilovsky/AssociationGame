

import java.io.*;
import java.util.*;

public class PlayGame
{
	public AssociationBoard board;
	public String[][] visibleBoard;
	public int[][] pointValues = new int[][]{{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2},{5,5,5,5}};
	public String[] visibleSolutions;
	public String finalSolution;
	public int boardSize = 4;
	public InputStreamReader isr;
	public BufferedReader br;
	public int[] scores;
	public char currentTeam;

	public PlayGame() throws IOException
	{	visibleBoard = new String[boardSize][boardSize];
		visibleBoard[0] = new String[]{"A1","B1","C1","D1"};
		visibleBoard[1] = new String[]{"A2","B2","C2","D2"};
		visibleBoard[2] = new String[]{"A3","B3","C3","D3"};
		visibleBoard[3] = new String[]{"A4","B4","C4","D4"};
		visibleSolutions = new String[]{"A","B","C","D"};
		board = new AssociationBoard();
		finalSolution = "Final";
		scores = new int[]{0,0};
		currentTeam = 'B';

		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);

	}

	public PlayGame(String input) throws IOException
	{
		visibleBoard[0] = new String[]{"A1","B1","C1","D1"};
		visibleBoard[1] = new String[]{"A2","B2","C2","D2"};
		visibleBoard[2] = new String[]{"A3","B3","C3","D3"};
		visibleBoard[3] = new String[]{"A4","B4","C4","D4"};
		visibleSolutions = new String[]{"A","B","C","D"};
		board = new AssociationBoard(input);
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}

	public boolean play() throws IOException
	{
		if (currentTeam == 'A') currentTeam = 'B';
		else currentTeam = 'A';

		printBoard();
		System.out.println("It is Team " + currentTeam +"'s Turn.");
		//enter which square you want to open.
		System.out.print("Please enter which spot you want to open: ");
		String input = br.readLine();
		if (openClue(input)) return true;
		printBoard();
		System.out.println("You can now guess one of the initial solutions or the final solution. Which one would you like to try (A,B,C,D,Final)?");
		input = br.readLine();
		if (input.equals("Final") || input.equals("final"))
		{
			return !checkFinal();
		}
		else return !checkMid(input);
		//allow them to guess the final word or one of the mid answers
		//check if they were right
		//if they were right about mid, open mid
		//if they were right about final, open final and return true
	

	}

	public void printBoard()
	{
		System.out.println("---------------------------------------------------------------------");

		for (int i = 0; i < boardSize; i++)
		{
			System.out.print("| ");
			for (int j = 0; j<boardSize;j++)
			{
				System.out.print(printableWord(visibleBoard[i][j]) + " | ");
			}
			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.print("| ");

		for (int k = 0; k< boardSize; k++)
		{
			System.out.print(printableWord(visibleSolutions[k]) + " | ");
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------");
		System.out.println("                           |" + printableWord(finalSolution) + "|                       ");
	}

	public String printableWord(String word)
	{
		int spacesToAdd = 14 - word.length();
		StringBuilder returnWord = new StringBuilder();
		if (spacesToAdd % 2 == 1)
		{
			for (int i = 0; i < spacesToAdd/2 ; i++)
			{
				returnWord.append(" ");
			}
			returnWord.append(word);
			for (int j = 0; j <= spacesToAdd/2 ; j++)
			{
				returnWord.append(" ");
			}
			return returnWord.toString();
		}
		else
		{
			for (int i = 0; i < spacesToAdd/2 ; i++)
			{
				returnWord.append(" ");
			}
			returnWord.append(word);
			for (int j = 0; j < spacesToAdd/2 ; j++)
			{
				returnWord.append(" ");
			}
			return returnWord.toString();
		}
	}



	public boolean openClue(String tile)
	{
		if (tile.length() != 2) 
		{
			System.out.println("That was not a valid input. Please enter a valid square.");
			return true;
		}
		char letter = tile.charAt(0);
		char number = tile.charAt(1);
		int row = Integer.parseInt(String.valueOf(number))-1;
		int col;

		switch (letter) 
		{
			case 'a':
			case 'A':
				col = 0;
				break;
			case 'b':
			case 'B':
				col = 1;
				break;
			case 'c':
			case 'C':
				col = 2;
				break;
			case 'd':
			case 'D':
				col = 3;
				break;
			default:
				col = 0;
				System.out.println("That was not a valid input. Please enter a valid square.");
				return true;
		}
		visibleBoard[row][col] = board.getClue(row, col);
		pointValues[row][col] = 0;
		return false;
	}

	public boolean checkMid(String tile) throws IOException
	{
		int col;
		switch (tile.charAt(0)) 
		{
			case 'a':
			case 'A':
				col = 0;
				break;
			case 'b':
			case 'B':
				col = 1;
				break;
			case 'c':
			case 'C':
				col = 2;
				break;
			case 'd':
			case 'D':
				col = 3;
				break;
			default:
				col = 0;
				System.out.println("That was not a valid input. Please enter a valid square next time.");
				return false;
		}
		System.out.println("Please enter your guess: ");
		String input = br.readLine();
		if (board.checkMidSolution(input,col))
		{
			scoreColumn(col);
			System.out.println("Thats correct, good job! Now you can try to guess the final solution!");
			visibleSolutions[col] = input;
			return checkFinal();
		}
		else 
		{
			System.out.println("Thats not quite right, better luck next time.");
			return false;
		}
		//gets there guess and sees if it is right
	}

	public boolean checkFinal() throws IOException
	{
		System.out.println("Enter your guess for the final solution: ");
		String input = br.readLine();
		if (board.checkFinalSoluton(input)) 
		{
			System.out.println("Wow thats amazing! You got it!");
			scoreColumn(0);
			scoreColumn(1);
			scoreColumn(2);
			scoreColumn(3);
			if (currentTeam == 'A') scores[0] += 15;
			else scores[1] += 15;
			finalSolution = input;
			return true;

		}
		System.out.println("Thats not quite right, better luck next time.");
		return false;

		//get there guess, see if its right
	} 
	public void getScores()
	{
		System.out.println("Team A has " + scores[0] + " points. Team B has " + scores[1] + " points.");
		if (scores[0] > scores[1])
			System.out.println("Team A Wins!");
		else if (scores[0] < scores[1])
			System.out.println("Team B Wins!");
		else
			System.out.println("Wow, its a tie!");
	}
	public void scoreColumn(int col)
	{
		int team;
		if (currentTeam == 'A') team = 0;
		else team = 1;
		for (int i = 0; i < 5;i++)
		{
			scores[team] += pointValues[i][col];
			pointValues[i][col] = 0;
		}
	}
}