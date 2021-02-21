
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PlayGame
{
	public GameBoard image;
	public AssociationBoard board;
	public String[][] visibleBoard;
	public int[][] pointValues = new int[][]{{2,2,2,2},{2,2,2,2},{2,2,2,2},{2,2,2,2},{5,5,5,5}};
	public String[] visibleSolutions;
	public String finalSolution;
	public int boardSize = 4;
	public int[] scores;
	public char currentTeam;

	public PlayGame() throws IOException
	{	visibleBoard = new String[boardSize][boardSize];
		visibleBoard[0] = new String[]{"         A1","         B1","         C1","         D1"};
		visibleBoard[1] = new String[]{"         A2","         B2","         C2","         D2"};
		visibleBoard[2] = new String[]{"         A3","         B3","         C3","         D3"};
		visibleBoard[3] = new String[]{"         A4","         B4","         C4","         D4"};
		visibleSolutions = new String[]{"         A","         B","        C","         D"};
		board = new AssociationBoard();
		finalSolution = "Final";
		scores = new int[]{0,0};
		currentTeam = 'B';
		image = new GameBoard();
		image.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);

	}

	public boolean play() throws IOException
	{
		if (currentTeam == 'A') currentTeam = 'B';
		else currentTeam = 'A';

		//enter which square you want to open.
		String input = JOptionPane.showInputDialog("Please enter which spot you want to open");
		if (openClue(input)) return true;
		input = JOptionPane.showInputDialog("You can now guess one of the initial solutions or the final solution.\nWhich one would you like to try (A,B,C,D,Final)?");
		if (input.equals("Final") || input.equals("final"))
		{
			return !checkFinal();
		}
		else return !checkMid(input);
	}

	public boolean openClue(String tile)
	{
		if (tile.length() != 2) 
		{
			JOptionPane.showMessageDialog(image,"That was not a valid input. \nPlease enter a valid square next time.");
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
				JOptionPane.showMessageDialog(image,"That was not a valid input. \nPlease enter a valid square next time.");
				return true;
		}
		visibleBoard[row][col] = board.getClue(row, col);
		image.setVisibleBoard(visibleBoard);
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
				JOptionPane.showMessageDialog(image,"That was not a valid input. \nPlease enter a valid square next time.");
				return false;
		}
		String input = JOptionPane.showInputDialog("Please enter your guess.");
		if (board.checkMidSolution(input,col))
		{
			scoreColumn(col);
			image.setScores(scores);
			JOptionPane.showMessageDialog(image,"Thats correct, good job! \nNow you can try to guess the final solution!");
			visibleSolutions[col] = input;
			image.setVisibleSolutions(visibleSolutions);
			return checkFinal();
		}
		else 
		{
			JOptionPane.showMessageDialog(image,"Thats not quite right, better luck next time.");
			return false;
		}
		//gets there guess and sees if it is right
	}

	public boolean checkFinal() throws IOException
	{
		String input = JOptionPane.showInputDialog("Enter your guess for the final solution.");
		if (board.checkFinalSoluton(input)) 
		{
			JOptionPane.showMessageDialog(image,"Wow thats amazing! You got it!");
			scoreColumn(0);
			scoreColumn(1);
			scoreColumn(2);
			scoreColumn(3);

			if (currentTeam == 'A') scores[0] += 15;
			else scores[1] += 15;
			image.setScores(scores);
			finalSolution = input;
			image.setFinal(finalSolution);
			return true;

		}
		//System.out.println("Thats not quite right, better luck next time.");
		return false;

		//get there guess, see if its right
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

	public void showGG()
	{
		String endMessage;
		if (scores[0] > scores[1])
			endMessage = "Team A Wins!";
		else if (scores[0] < scores[1])
			endMessage = "Team B Wins!";
		else
			endMessage = "Wow, its a tie!";
		image.endGame(endMessage);
	}


}