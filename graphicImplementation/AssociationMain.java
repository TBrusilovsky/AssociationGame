
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class AssociationMain
{
	public static void main(String[] args) throws IOException
	{
		PlayGame game= new PlayGame();
		boolean inGame = true;
		int rounds = 0;


		
		while (inGame)
		{
			rounds++;
			inGame = game.play();
		}
		game.showGG();

	}
}