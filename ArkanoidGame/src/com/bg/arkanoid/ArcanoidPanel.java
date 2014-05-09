package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import com.bg.arkanoid.etypes.ETypeOfBlock;
import com.bg.arkanoid.images.Images;
import com.bg.arkanoid.sound_effects.SoundEffects;

public class ArcanoidPanel extends JPanel implements ActionListener, KeyListener {
	
	
	private static final long serialVersionUID = 1L;

	private static final int DELAY_OF_REFRESHING = 5;
	
	private static final String URL_OF_BACK_PICTURE = "res/back1.jpg";

	private static final int BLUR_NUMBER = 15;

	private static final int DEFAULT_BAT_DELTA_X = 7;

	private static final int SIZE_OF_TEXT = 50;
	
	Timer mainTimer;
	//SoundEffects soundEff;
	static Images imageLoader = new Images();
	
	public static LinkedList<Brick> bricks = new LinkedList<Brick>();
	public static Player player;
	public static Ball ball = new Ball();
	public static Bat bat = new Bat();
	public static Line2D.Double up = new Line2D.Double(0 ,0, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0);
	public static Line2D.Double down = new Line2D.Double(0 ,Toolkit.getDefaultToolkit().getScreenSize().getHeight(), Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public static Line2D.Double left = new Line2D.Double(0 ,0, 0, Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public static Line2D.Double right = new Line2D.Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
	BufferedImage backImage;
	
	ArcanoidPanel(){
		imageLoader = new Images();
		imageLoader.loadImages();
		
		new CoordReader().readCoordinates();
		
		//setBackground(bricks.get(bricks.size() - 2).getColor());
		setBackground(Color.DARK_GRAY);
		
		player = new Player("Roman");
		
		ball = new Ball();
		//ball.setColor(bricks.get(bricks.size() - 1).getColor());
		ball.setColor(Color.YELLOW);
		ball.setAddDeltaX(0);
		ball.setAddDeltaY(0);
		
		bat.setCoord(new Point(   (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - bat.getSize().getWidth() / 2),  (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - bat.getSize().getHeight())));
		bat.setColor(Color.RED);
		
		mainTimer = new Timer(DELAY_OF_REFRESHING, this);
		
		//soundEff = new SoundEffects();
		//soundEff.loadSounds();
		imageLoader = new Images();
		imageLoader.loadImages();
		
		//Loading background
		try {
			backImage = ImageIO.read(new File(URL_OF_BACK_PICTURE));
			
			float[] matrix = new float[BLUR_NUMBER * BLUR_NUMBER];
			for (int i = 0; i < BLUR_NUMBER * BLUR_NUMBER; i++)
				matrix[i] = 1.0f / (BLUR_NUMBER * BLUR_NUMBER);
		    BufferedImageOp op = new ConvolveOp( new Kernel(BLUR_NUMBER, BLUR_NUMBER, matrix), ConvolveOp.EDGE_NO_OP, null );
		    backImage = op.filter(backImage, null);
		
			MediaTracker media_tracker = new MediaTracker(this);
			media_tracker.addImage(backImage, 0);
			try{
		        media_tracker.waitForID(0);
			}catch(InterruptedException e){
			    System.out.println("Image loading was interrupted");
			}
		}catch(Exception e){}
		
		mainTimer.start();
		
		addKeyListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//Ball placing
		Point newLocation = new Point();
		newLocation.setLocation(ball.getCoord().getX() + ball.getDeltaX() + ball.getAddDeltaX() ,  ball.getCoord().getY() + ball.getDeltaY() + ball.getAddDeltaY());
		ball.setCoord(newLocation);
		
		//Checking blocks' intersections
		List<Brick> brickToDelete = new LinkedList<Brick>();
		for(Brick b : bricks){
			if(b.getRect().intersects(ball.getRect())){
				if(  (ball.getRect().intersectsLine(b.getUpSide())) || (ball.getRect().intersectsLine(b.getDownSide()))) {
					ball.setDeltaY( - ball.getDeltaY());
					ball.hit();
					brickToDelete.add(b);
				}else if(  (ball.getRect().intersectsLine(b.getLeftSide()))  ||  (ball.getRect().intersectsLine(b.getRightSide()))   ){
					ball.setDeltaX( - ball.getDeltaX());
					ball.hit();
					brickToDelete.add(b);
				}
			}
		}
		for(Brick b : brickToDelete)
			Brick.remove(b);
			//bricks.remove(b);
		
		//Placing bat
		if(bat.getDeltaX() != 0){
			if(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()).contains(new Rectangle((int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY(), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight())))
				bat.setCoord(new Point( (int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY()));
		}
		
		
		//Checking bat's intersections
		if(ball.getRect().intersectsLine(bat.getUp0())){
			
			if(ball.getDeltaX() > 0){
				ball.setDeltaX( - ball.getDeltaX());
				ball.setDeltaY( - ball.getDeltaY());
			}else if(ball.getDeltaX() < 0){
				ball.setDeltaX( + ball.getDeltaX() * 1.3);
				ball.setDeltaY( - ball.getDeltaY() / 1.3);
			}else{
				ball.setDeltaX( - Ball.DEFAULT_DELTA_X * 1.3);
				ball.setDeltaY( - ball.getDeltaY() / 1.3);
			}
			
		}else if(ball.getRect().intersectsLine(bat.getUp1())){
			
			if(ball.getDeltaX() > 0){
				ball.setDeltaX( - ball.getDeltaX() * 1.1);
				ball.setDeltaY( - ball.getDeltaY() / 1.1);
			}else if(ball.getDeltaX() < 0){
				ball.setDeltaX( + ball.getDeltaX() / 1.1);
				ball.setDeltaY( - ball.getDeltaY() * 1.1);
			}else{
				ball.setDeltaX( - Ball.DEFAULT_DELTA_X * 1.1);
				ball.setDeltaY( - ball.getDeltaY() / 1.1 );
			}
		
		
		}else if(ball.getRect().intersectsLine(bat.getUp2())){
			
			ball.setDeltaY( - ball.getDeltaY());
		
		}else if(ball.getRect().intersectsLine(bat.getUp3())){
			
			if(ball.getDeltaX() > 0){
				ball.setDeltaX( + ball.getDeltaX() * 1.1);
				ball.setDeltaY( - ball.getDeltaY() / 1.1);
			}else if(ball.getDeltaX() < 0){
				ball.setDeltaX( - ball.getDeltaX() / 1.1);
				ball.setDeltaY( - ball.getDeltaY() * 1.1);
			}else{
				ball.setDeltaX( + Ball.DEFAULT_DELTA_X * 1.1);
				ball.setDeltaY( - ball.getDeltaY() / 1.1 );
			}
		
		
		}else if(ball.getRect().intersectsLine(bat.getUp4())){
			
			if(ball.getDeltaX() > 0){
				ball.setDeltaX( + ball.getDeltaX() * 1.3);
				ball.setDeltaY( - ball.getDeltaY() / 1.3);
			}else if(ball.getDeltaX() < 0){
				ball.setDeltaX( - ball.getDeltaX());
				ball.setDeltaY( - ball.getDeltaY());
			}else{
				ball.setDeltaX( + Ball.DEFAULT_DELTA_X * 1.3);
				ball.setDeltaY( - ball.getDeltaY() / 1.3 );
			}
		
		
		}else if(   (ball.getRect().intersectsLine(bat.getLeft()))  ||  (ball.getRect().intersectsLine(bat.getRight()))   ){
			ball.setDeltaX( - ball.getDeltaX());
			ball.setDeltaY(ball.getDeltaY() + 0.5);
		}
		
		
		//Out-of-screen block
		
		if(ball.getCoord().getX() + ball.getDiameter() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()){
			ball.setDeltaX( - ball.getDeltaX());
		}if(ball.getCoord().getY() <= 0){
			ball.setDeltaY( - ball.getDeltaY());
		}if(ball.getCoord().getX() < 0){
			ball.setDeltaX( - ball.getDeltaX());
		}
		
		
		
		//Checking lose
		if( ball.getRect().intersectsLine(down) ){
			
			ball.setDeltaY( - ball.getDeltaY());
			
			if(player.getLifes() == 0)
				player.endGame();
			else
				player.setLifes(player.getLifes() - 1);
			
		}
		
		repaint();
	}
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		//Low speed with antialiasing
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//g.drawImage(backImage, 0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);
		
		for(Brick brick : bricks){
			g.setColor(brick.getColor());			
			g.fillRoundRect(brick.getCoord().x, brick.getCoord().y, (int)brick.getSize().getWidth(), (int)brick.getSize().getHeight(), (int)brick.getSize().getWidth() / 5, (int)brick.getSize().getHeight() / 5);
			
			if(brick.getTypeOfBlock() == ETypeOfBlock.ARMORED_BLOCK){
				if(brick.getHitsToDestroy() > 1){
					g.setColor(brick.getColor().darker().darker());
					g.drawLine(brick.getCoord().x, (int)(brick.getCoord().y + brick.getSize().getHeight() / 2), (int)(brick.getCoord().x + brick.getSize().getWidth()), (int)(brick.getCoord().y + brick.getSize().getHeight() / 2));
				}
			}
		}
		
		g.setColor(ball.getColor());
		g.fillArc((int)ball.getCoord().getX(), (int)ball.getCoord().getY(), (int)ball.getDiameter(), (int)ball.getDiameter(), 0, 360);
	
		
		g.setColor(bat.getColor());
		g.fillRoundRect(bat.getCoord().x, bat.getCoord().y, (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight(), (int)bat.getSize().getWidth() / 2, (int)bat.getSize().getHeight() / 2);
	
		
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(200, 200, 0, 150));
		g.setFont(new Font("font", Font.BOLD, SIZE_OF_TEXT));
		
		g.drawString(String.valueOf(player.getScores()), 10, 50);
		
		g.setColor(new Color(20, 200, 20, 150));
		g.drawString(String.valueOf(player.getLifes()), 10, 100);
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 37){
			bat.setDeltaX( - DEFAULT_BAT_DELTA_X);
		}else if(e.getKeyCode() == 39){
			bat.setDeltaX(DEFAULT_BAT_DELTA_X);
		}
		
		if(e.getKeyCode() == 40){
			//ball.setAddDeltaX(ball.getDeltaX() * 4);
			//ball.setAddDeltaY(ball.getDeltaY() * 4);
			ball.setDeltaY(ball.getDeltaY() + 0.2);
		}
	}

	public void keyReleased(KeyEvent e) {
		if( (e.getKeyCode() == 37 ) || (e.getKeyCode() == 39) ){
			bat.setDeltaX(0);
		}
		if(e.getKeyCode() == 40){
			ball.setAddDeltaX(0);
			ball.setAddDeltaY(0);
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
