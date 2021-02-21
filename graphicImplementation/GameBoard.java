import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameBoard extends JFrame 
{
	public String[][] visibleBoard;
	public String[] visibleSolutions;
	public String finalSolution;
	public int[] scores;
	public char currentTeam;
	public int roundNumber;
	public boolean done = false;
	public String endMessage;
 


	public GameBoard()
	{
		super(" Association Game ");
		setSize(1030,500);

		visibleBoard = new String[4][4];
		visibleBoard[0] = new String[]{"         A1","         B1","         C1","         D1"};
		visibleBoard[1] = new String[]{"         A2","         B2","         C2","         D2"};
		visibleBoard[2] = new String[]{"         A3","         B3","         C3","         D3"};
		visibleBoard[3] = new String[]{"         A4","         B4","         C4","         D4"};
		visibleSolutions = new String[]{"         A","         B","        C","         D"};
		finalSolution = "Final";
		scores = new int[]{0,0};
		currentTeam = 'A';
		roundNumber = 1;
		setVisible(true);

	}
	public void setVisibleBoard(String[][] updatedBoard)
	{
		visibleBoard = updatedBoard;
		repaint();
	}
	public void setVisibleSolutions(String[] updatedSolutions)
	{
		visibleSolutions = updatedSolutions;
		repaint();
	}
	public void setFinal(String updatedFinal)
	{
		finalSolution = updatedFinal;
		repaint();
	}
	public void setScores(int[] updatedScores)
	{
		scores = updatedScores;
		repaint();
	}
	public void setTeam(char updatedTeam)
	{
		currentTeam = updatedTeam;
		repaint();
	}
	public void endGame(String message)
	{
		endMessage = message;
		done = true;
		repaint();
	}

	public void paint(Graphics g)
	{
		if (!done)
		{
			for (int i = 1; i<= 4; i++) //makes the board
			{
				for (int j = 1; j<=4; j++)
				{
					g.setColor(Color.blue); 
					g.fillRect(155*i-50,50+55*j,150,50);
					g.setColor(Color.white); 
					g.drawString(visibleBoard[j-1][i-1],155*i-30,80+55*j);
				}
				g.setColor(Color.blue); 
				g.fillRect(155*i-50,340,150,50);
				g.setColor(Color.white); 
				g.drawString(visibleSolutions[i-1],155*i-30,370);
			}
			g.setColor(Color.blue); 
			g.fillRect(340,410,150,50); //makes the final
			g.fillRect(750,105,200,355); //makes the score box
			g.setColor(Color.white); 
			g.drawString(finalSolution ,360,440);
			g.drawString("It is Team " + currentTeam + "'s turn.",770,135);
			g.drawString("Team A has " + scores[0] + " points.", 770,175);
			g.drawString("Team B has " + scores[1] + " points.", 770,215);
		}
		if (done)
		{
			g.setColor(Color.red);
			g.fillRect(415,175,200,150);
			g.drawString(endMessage, 445,205);
		}
	} 
}