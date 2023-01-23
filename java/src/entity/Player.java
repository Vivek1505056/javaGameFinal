package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity 
{
	
	GamePanel gp;
	KeyHandler keyH;
	 
	public Player(GamePanel gp, KeyHandler keyH)
	{
		this.gp = gp;
		this.keyH = keyH;
				
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues()
	{
		x = 550;
		y = 800;
		speed = 10;
		direction = "";

	}
	
	public void getPlayerImage() {
		try {
			 
			stationary = ImageIO.read(new File("res/player/spaces.png"));

			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getXVal()
	{
		return x;
	}
	
	public int getYVal()
	{
		return y;
	}
	public void update()
	{

		if(keyH.leftPressed == true)
		{
			x -= speed;
			direction = "still";
		}
		
		if(keyH.rightPressed == true)
		{
			x += speed;
			direction = "still";
		}
		
		else
		{
			direction = "still";
		}
		 		
	}

	
	public void draw(Graphics2D g2)
	{		
		
		BufferedImage image = null;
		
		switch(direction) {
		case "left":
			image = stationary;
			break;
		case "right":
			image = stationary;
			break;
			
		case "still":
			image = stationary;
			break;
		}

		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
