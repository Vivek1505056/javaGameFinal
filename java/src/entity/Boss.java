package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Boss extends Entity 
{
	
	GamePanel gp;
	
	public Boss(GamePanel gp)
	{
		this.gp = gp;		
		
		
		setDefaultValues();
		getBossImage();
		
	}
	
	public void setDefaultValues()
	{
		x = -30;
		y = 0;
		speed = 10;
		life = 100;
	}
	
	public void getBossImage() {
		try {
			
			stationary = ImageIO.read(new File("res/boss/sun.png"));

			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update()
	{

	}
	
	public void checkCollision()
	{
	
	}
	
	public void draw(Graphics2D g2)
	{		
		
		BufferedImage image = null;
		
		image = stationary;
		
		g2.drawImage(image, x, y, 1500, 1000, null);
	}
}

