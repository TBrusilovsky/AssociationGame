import java.io.*;
import java.util.*;

public class AssociationBoard
{
	private AssociationList L;
	private String finalSolution;
	private String[] midSolutions;
	private String[][] clueWords;
	private ArrayList<String> usedWords;
	private int boardSize = 4;

	public AssociationBoard(String startingWord)
	{
		finalSolution = startingWord;
		L = new AssociationList();
		usedWords = new ArrayList<String>();
		usedWords.add(startingWord);

		loadMid();
		loadClues();


	}
	public AssociationBoard()
	{
		L = new AssociationList();
		usedWords = new ArrayList<String>();

		loadFinal();
		loadMid();
		loadClues();

	}

	private void loadFinal()
	{
		finalSolution - L.randomWord();
	}
	private void loadMid()
	{
		midSolutions = new String[boardSize];
		ArrayList<String> solutionList = L.associatedWords(boardSize,finalSolution,usedWords);

		for (int i = 0; i < boardSize; i++)
		{
			midSolutions[i] = solutionList.get(i);
			usedWords.add(solutionList.get(i));
		}

	}
	private void loadClues()
	{

	}




}