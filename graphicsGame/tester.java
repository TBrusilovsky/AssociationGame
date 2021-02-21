

import java.io.*;
import java.util.*;
import java.awt.event.*;
public class tester
{
	public static void main(String args[]) throws IOException
	{
		GameBoard picture = new GameBoard();
		picture.addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
	}
}