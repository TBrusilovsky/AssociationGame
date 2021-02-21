

import java.io.*;
import java.util.*;

public class AssociationList
{
	private ArrayList<String> words = new ArrayList<String>();
	private HashMap<String, String[]> wordAssociations = new HashMap<String, String[]>();
	private Random R = new Random();

	public AssociationList() throws IOException
	{
		Scanner infile = new Scanner(new FileInputStream("WordList.txt"));
		String line;
		String[] splitLine;
		while (infile.hasNext())
		{
			line = infile.nextLine();
			splitLine = line.split(" ");
			words.add(splitLine[0]);
			wordAssociations.put(splitLine[0],splitLine);
		}
		infile.close();
	}

	public String randomWord() //returns a random word with associatons
	{
		return words.get(R.nextInt(words.size()));
	}

	public ArrayList<String> associatedWords(int numberOfWords, String key, ArrayList<String> bannedWords) //returns an arraylist of words associated with the key.
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		int count = 0;
		String wordToAdd;

		if (!words.contains(key)) return returnArray; //key is not in the input file

		String[] keyArray = wordAssociations.get(key);

		if (keyArray.length < numberOfWords) return returnArray; //there are not enough words assoiciated with the key

		while (count < numberOfWords)
		{
			wordToAdd = keyArray[R.nextInt(keyArray.length)];
			if (wordToAdd.charAt(0) == '*') wordToAdd = wordToAdd.substring(1,wordToAdd.length());
			if (!returnArray.contains(wordToAdd) && !bannedWords.contains(wordToAdd)) //makes sure it can't add a repeat or a word already used in the board
			{
				returnArray.add(wordToAdd);
				count++;
			}
		}
		return returnArray;

	}
	public ArrayList<String> associatedWordsStarred(int numberOfWords, String key, ArrayList<String> bannedWords) //returns an arraylist of words associated with the key.
	{
		ArrayList<String> returnArray = new ArrayList<String>();
		int count = 0;
		String wordToAdd;

		if (!words.contains(key)) return returnArray; //key is not in the input file

		String[] keyArray = wordAssociations.get(key);

		if (keyArray.length < numberOfWords) return returnArray; //there are not enough words assoiciated with the key

		while (count < numberOfWords)
		{
			wordToAdd = keyArray[R.nextInt(keyArray.length)];

			if (wordToAdd.charAt(0) == '*') { //only works if the word has a *
				wordToAdd = wordToAdd.substring(1,wordToAdd.length());
				if (!returnArray.contains(wordToAdd) && !bannedWords.contains(wordToAdd) ) //makes sure it can't add a repeat or a word already used in the board
				{
					returnArray.add(wordToAdd);
					count++;
				}
			}
		}
		return returnArray;

	}

} 