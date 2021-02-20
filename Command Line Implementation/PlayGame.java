package associationGame;


import java.io.*;
import java.util.*;
import java.util.regex.*;

public class PlayGame
{
	public AssociationBoard board;
	public String[][] visibleBoard;
	public String[] visibleSolutions;
	public int boardSize = 4;
	public InputStreamReader isr;
	public BufferedReader br;

	public PlayGame()
	{
		board = new AssociationBoard();
		visibleBoard = {{"A1","B1","C1","D1"},{"A2","B2","C2","D2"},{"A3","B3","C3","D3"},{"A4","B4","C4","D4"}};
		visibleSolutions = {"A","B","C","D"};
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);

	}

	public PlayGame(String input)
	{
		board = new AssociationBoard(input);
		visibleBoard = {{"A1,B1,C1,D1"},{"A2,B2,C2,D2"},{"A3,B3,C3,D3"},{"A4,B4,C4,D4"}};
		isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}

	public boolean play()
	{
		printBoard();

		//enter which square you want to open.
		System.out.println("Please enter which clue you want to open: ");
		String input = br.readLine();
		openClue(input);
		printBoard();
		//allow them to guess the final word or one of the mid answers
		//check if they were right
		//if they were right about mid, open mid
		//if they were right about final, open final and return true
		return false;

	}

	public void printBoard()
	{
		for (int i = 0; i < boardSize; i++)
		{
			for (int j = 0; j<boardSize;j++)
			{
				System.out.print(visibleBoard[i][j] + " | ");
			}
			System.out.println();
		}
		for (int k = 0; k< boardSize; k++)
		{
			System.out.print(visibleSolutions[k] + " | ");
		}
		System.out.println();
	}

	public void openClue(String tile)
	{
		char letter = tile.charAt(0);
		char number = tile.charAt(1);
		int row = ((int) number)-1;
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
				System.out.println("There was an error. Please enter a valid square.");
		}
		visibleBoard[row][col] = board.getClue(row, col);
	}

	public boolean checkMid(String tile)
	{
		//gets there guess and sees if it is right
	}

	public boolean checkFinal()
	{
		//get there guess, see if its right
	} 
}