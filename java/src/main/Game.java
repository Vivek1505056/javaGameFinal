package main;

import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class Game 
{
	
	public static void main(String[] args)
	{
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Java in Space");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
		try {
		      File myObj = new File("src/file.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		    }
	}
	
}
