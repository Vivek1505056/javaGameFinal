package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.*;

import entity.Boss;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// screen settings
	static final int originalTileSize = 16;
	static final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 24;
	public final int maxScreenRow = 20;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//images
	
	public static BufferedImage fireImg, bulletImg, gameoverImg, winImg;
	static {
		try {
			fireImg = ImageIO.read(new File("src/fire.png"));
			
		}catch(IOException e) {
			System.out.print("can't load fire");
		}
	}
	static {
		try {
			bulletImg = ImageIO.read(new File("src/pixil-frame-0 (1).png"));
			
		}catch(IOException e) {
			System.out.print("can't load fire");
		}
	}

	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	Boss boss = new Boss(this);
	Random randNum = new Random();
	Rectangle health = new Rectangle();
	
	//JLabels for game over screen and win screen
	JLabel win = new JLabel("VICTORY"){
		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			Rectangle r = g.getClipBounds();
			g.fillRect(r.x,r.y,r.width,r.height);
			super.paintComponent(g);
		}
	};
	JLabel lose = new JLabel("GAME<br>OVER") {
		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			Rectangle r = g.getClipBounds();
			g.fillRect(r.x,r.y,r.width,r.height);
			super.paintComponent(g);
		}
	};
	
	
        	
   	//make variables
	int life = 100;
	int playerX = 550;
	int playerY = 800;
	int playerSpeed = 15;
	
	int bossX = -30;
	int bossY = 0;
	
	int FPS = 60;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double maxWidth = screenSize.getWidth();
    double maxHeight = screenSize.getHeight();
    
    int rand;
	int originalFireY = (int) (maxHeight-900);
    int fireY = (int) (maxHeight - 900);
    int fireX = rand;
    int fireX2 = rand;
    int fireX3 = rand;
    int fireX4 = rand;
    int fireX5 = rand;
    int fireX6 = rand;
    int fireX7 = rand;
    int fireX8 = rand;
    int fireX9 = rand;
    int fireX10 = rand;
    
    int bulletY = playerY;
    int bulletX = playerX;
    int bulletSpeed = 40;
    int bulletHeight = 50;
    int bulletWidth = 50;
    
    int fireSpeed = 10;
    int fireHeight = 80;
    int fireWidth = 50;
    boolean gameOver;
    int counter;
    int healthWidth = 1000;
    
    int timer;
    int timerSeconds = 0;
	
	public GamePanel()
	{
		//more screen stuff
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() 
	{
		//making sure the drawings print and update
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null)
		{			
			
			// 1 UPDATE
			update();
			
			// 2 DRAW
			repaint();
			
			
			try {
				
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0)
				{
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update()
	{
		
		//making the bullet move
		if(keyH.spacePressed == true)
		{
			bulletY -= bulletSpeed;
		}
		
		//sprites update movements
		player.update();
		boss.update();
		
		//random locations for the fire to spawn
		playerX = player.getXVal();
		playerY = player.getYVal();
		if(fireX < 0 || fireY > maxHeight) {
            fireY = originalFireY;
            rand = randNum.nextInt((int) (screenWidth));
            fireX = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX2 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX3 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX4 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX5 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX6 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX7 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX8 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX9 = rand;
            rand = randNum.nextInt((int) (screenWidth));
            fireX10 = rand;
    
        }
		
		//collisions for each fire
		if(((playerX > fireX) && (playerX < fireX + fireWidth)) && ((playerY > fireY + 70) && (playerY < fireY + 70 + fireHeight))){
            gameOver = true;

        }
		if(((playerX > fireX2) && (playerX < fireX2 + fireWidth)) && ((playerY > fireY + 100) && (playerY < fireY + 100 + fireHeight))){
            gameOver = true;
		}
        
		if(((playerX > fireX3) && (playerX < fireX3 + fireWidth)) && ((playerY > fireY + 150) && (playerY < fireY  + 150 + fireHeight))){
            gameOver = true;

        }
		
		if(((playerX > fireX4) && (playerX < fireX4 + fireWidth)) && ((playerY > fireY + 100) && (playerY < fireY + 100 + fireHeight))){
            gameOver = true;

        }
		if(((playerX > fireX5) && (playerX < fireX5 + fireWidth)) && ((playerY > fireY + 92) && (playerY < fireY + 92 + fireHeight))){
            gameOver = true;

        }
		if(((playerX > fireX6) && (playerX < fireX6 + fireWidth)) && ((playerY > fireY + 20) && (playerY < fireY + 20 + fireHeight))){
            gameOver = true;

		}
		
		if(((playerX > fireX7) && (playerX < fireX7 + fireWidth)) && ((playerY > fireY + 200) && (playerY < fireY + 200 + fireHeight))){
            gameOver = true;

        }
		if(((playerX > fireX8) && (playerX < fireX8 + fireWidth)) && ((playerY > fireY + 175) && (playerY < fireY + 175 + fireHeight))){
            gameOver = true;

			
		}
		if(((playerX > fireX9) && (playerX < fireX9 + fireWidth)) && ((playerY > fireY) && (playerY < fireY + fireHeight))){
            gameOver = true;

        }
		
		if(((playerX > fireX10) && (playerX < fireX10 + fireWidth)) && ((playerY > fireY + 256) && (playerY < fireY + 256 + fireHeight))){
            gameOver = true;

        }
		
		//boss hp and hitbox
		if((bulletX > bossX) && (bulletX<bossX+1500) && (bulletY>bossY) && (bulletY<bossY+256)){
			bulletY = playerY;
			life = life - 1;
			healthWidth -= 10;
			if(life <= 0)
			{
				fireSpeed = 0; 
			}
		} 

		

		//making fire move
		fireY += fireSpeed;
		
		if(timer % 60 == 0 && timer != 0)
		{
			timerSeconds += 1;
		}
		timer += 1;
		
		
	}
	
	public void paintComponent(Graphics g)
	{
		//drawing all the images
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		boss.draw(g2);
		
		g2.setColor(Color.red);
		
		g2.drawImage(fireImg, fireX, fireY + 100, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX2, fireY + 70, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX3, fireY + 150, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX4, fireY + 100, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX5, fireY + 92, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX6, fireY + 20, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX7, fireY + 200, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX8, fireY + 175, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX9, fireY, fireWidth, fireHeight, null);
		g2.drawImage(fireImg, fireX10, fireY + 256, fireWidth, fireHeight, null);
		
		g2.fillRect(70, 875, healthWidth, 15);
		g2.drawRect(70, 875, healthWidth, 15);
		
		
		
		if(keyH.spacePressed == true)
		{
			bulletX = playerX;
			g2.drawImage(bulletImg, bulletX, bulletY, bulletWidth, bulletHeight, null);
		}
		
		if(keyH.spacePressed == false)
		{
			bulletY = playerY;
		}
		
		//making game over and win screen
		if(gameOver == true)
		{
			lose.setHorizontalAlignment(SwingConstants.CENTER);
			lose.setVerticalAlignment(JLabel.CENTER);
			lose.setForeground(Color.WHITE);
			lose.setFont(getFont().deriveFont(Font.BOLD,100f));
			if(lose.getText().equals("GAME<br>OVER")){
				lose.setText("<html><div style='text-align: center;'>"+lose.getText()+"<br><br>Time Alive: "+timerSeconds+"s</html>");
			}
			
			this.add(lose, BorderLayout.CENTER);
			this.setVisible(true);
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			((JPanel)frame.getGlassPane()).setLayout(new BorderLayout());
			((JPanel)frame.getGlassPane()).add(lose, BorderLayout.CENTER);
			frame.getGlassPane().setBackground(new Color(0, 0, 0, 100));
			frame.getGlassPane().setVisible(true);
			lose.setBackground(new Color(0, 0, 0, 100));
			lose.setOpaque(false);

			fireSpeed = 100;
			playerSpeed = 0;
			bulletSpeed = 0;
			
			
		}
		

		
		if(life <= 0)
		{
			win.setHorizontalAlignment(JLabel.CENTER);
			win.setVerticalAlignment(JLabel.CENTER);
			win.setForeground(Color.WHITE);
			win.setFont(getFont().deriveFont(Font.BOLD,100f));
			win.setText("<html><div style='text-align: center;'>875/"+win.getText()+"<br><br>Time Alive: "+timerSeconds+"s</html>");
			this.add(win, BorderLayout.CENTER);
			this.setVisible(true);
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			((JPanel)frame.getGlassPane()).setLayout(new BorderLayout());
			((JPanel)frame.getGlassPane()).add(win, BorderLayout.CENTER);
			frame.getGlassPane().setBackground(new Color(0, 0, 0, 100));
			frame.getGlassPane().setVisible(true);
			win.setBackground(new Color(0, 0, 0, 100));
			win.setOpaque(false);
			gameOver = false;
			playerSpeed = 0;
		}
		
		Scanner sc;
		
		if(gameOver == true)
		{
			try {
                sc = new Scanner(new File("src/file.txt"));
                
                StringBuffer buffer = new StringBuffer();

                while (sc.hasNextLine()) {
                    buffer.append(sc.nextLine());
                }
                String fileContents = buffer.toString();
                if(Integer.valueOf(fileContents.substring(0,fileContents.length()-1)) < timerSeconds){
                    fileContents = fileContents.replaceAll(fileContents, ""+timerSeconds+"s");
                    try {
                        BufferedWriter myWriter = new BufferedWriter(new FileWriter(new File("src/file.txt")));
                        myWriter.write(Integer.toString(timerSeconds)+"s");
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                        }
                }



                sc.close();
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
			timerSeconds = 0;
		}
		g2.dispose();
		
	}
	
	
}