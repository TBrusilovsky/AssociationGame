package associationGame;


import java.io.*;
import java.util.*;

public class AssociationBoard
{
	private AssociationList l;
	private String finalSolution;
	private String[] midSolutions;
	private String[][] clueWords;
	private ArrayList<String> usedWords;
	private int boardSize = 4;

	public AssociationBoard(String startingWord)
	{
		finalSolution = startingWord;
		l = new AssociationList();
		usedWords = new ArrayList<String>();
		usedWords.add(startingWord);

		loadMid();
		loadClues();
	}

	public AssociationBoard()
	{
		l = new AssociationList();
		usedWords = new ArrayList<String>();

		loadFinal();
		loadMid();
		loadClues();
	}

	private void loadFinal()
	{
		finalSolution = l.randomWord();
	}
	private void loadMid()
	{
		midSolutions = new String[boardSize];
		ArrayList<String> solutionList = l.associatedWords(boardSize,finalSolution,usedWords);

		for (int i = 0; i < boardSize; i++)
		{
			midSolutions[i] = solutionList.get(i);
			usedWords.add(solutionList.get(i));
		}
	}
	private void loadClues()
	{
		clueWords = new String[boardSize][boardSize];

		for (int i = 0; i <boardSize;i++)
		{
			ArrayList<String> wordList = l.associatedWords(boardSize,midSolutions[i],usedWords);

			for (int j = 0; j < boardSize; j++)
			{
				clueWords[i][j] = wordList.get(j);
				usedWords.add(wordList.get(j));
			}
		}
	}

	public String getClue(int r, int c)
	{
		return clueWords[r][c];
	}

	public boolean checkMidSolution(String guess, int c)
	{
		if (midSolutions[c].equals(guess)) return true;
		return false;
	}

	public boolean checkFinalSoluton(String guess)
	{
		if (finalSolution.equals(guess)) return true;
		return false;
	}




}