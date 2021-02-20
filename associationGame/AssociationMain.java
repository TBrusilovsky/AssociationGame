
import java.io.*;
import java.util.*;

public class AssociationMain
{
	public static void main(String[] args) throws IOException
	{
		PlayGame game= new PlayGame();
		boolean inGame = true;
		int rounds = 0;

		/*if (args.length == 0)
		{
			game = new PlayGame();
		}
		else {
			game = new PlayGame(args[0]);
		} */

		System.out.println("Welcome to the Association Game.");

		while (inGame)
		{
			rounds++;
			inGame = game.play();
		}
		game.printBoard();
		System.out.println("Well Played! You finished the game in " + rounds + " rounds!");


		System.exit(0);
	}
}