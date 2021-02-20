package AssociationGame;

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main 
{
	public static JFrame frame;

	public static void main(String[] args)
	{
		frame = new JFrame("Experment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		JLabel lable = new JLabel("I am a Lable",SwingConstants.CENTER); 
		lable.setPreferredSize(new Dimension(300, 100)); 
		frame.getContentPane().add(lable, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.pack(); 
		frame.setVisible(true); 
	}

}