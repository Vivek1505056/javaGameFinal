package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		
		tile = new Tile[5];
		
		getTileImage();
	}
	
	public void getTileImage()
	{
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(new File("res/tiles/stars.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
	
		g2.drawImage(tile[0].image, 749, 583, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, 200, 530, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, 560, 421, gp.tileSize, gp.tileSize, null);
		g2.drawImage(tile[0].image, 938, 428, gp.tileSize, gp.tileSize, null);

	}
}