package associationGame;

import java.io.*;
import java.util.*;

public class AssociationMain
{
	public static PlayGame game;
	public static boolean inGame = true;
	public static int rounds = 0;


	public static void main(String[] args) throws IOException
	{

		if (args.length == 0)
		{
			game = new PlayGame();
		}
		game = new PlayGame(args[0]);

		System.out.println("Welcome to the Association Game. ");

		while (inGame)
		{
			inGame = game.play();
		}
		System.out.println("Well Played! You finished the game in " + rounds + " rounds!");

		System.exit(0);
	}
}