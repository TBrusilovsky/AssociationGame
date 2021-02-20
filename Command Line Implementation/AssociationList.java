import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AssociationList
{
	private ArrayList<String> words = new ArrayList<String>();
	private HashMap<String, String[]> wordAssociations = new HashMap<String, String[]>();
	private Random R = new Random()

	public AssociationList()
	{
		Scanner infile = new Scanner(new FileInputStream("WordAssociations.txt"));
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

	public String randomWord()
	{
		return words.get(R.nextInt(words.size()));
	}

	public ArrayList<String> associatedWords(int numberOfWords, String key)
	{
		if (!words.contains(key)) return new ArrayList<String>(); //key is not in the input file

		String[] keyArray = wordAssociations.get(key);

		if (keyArray.length < numberOfWords); return new ArrayList<String>(); //there are not enough words assoiciated with the key

		ArrayList<String> returnArray = new ArrayList<String>();
		int count = 0;
		String wordToAdd
		while (count < numberOfWords)
		{
			wordToAdd = keyArray[R.nextInt(keyArray.length)];
			if (!returnArray.contains(wordToAdd))
			{
				returnArray.add(wordToAdd);
				count++;
			}
		}
		return returnArray;

	}




} 