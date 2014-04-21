package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements ActionListener, KeyListener{
	

	private static  final  int MIN_BRIGHTNESS_OF_COLOR = 0;
	public  static  final  int DEFAULT_DIAMETER_OF_SHIP = 30;
	private static  final  int DELAY_OF_REFRESHING = 5;
	public  static  final  int DEFAULT_SPEED = 1;
	public  static  final  int DEFAULT_TRANSPARENCY_OF_CIRCLES = 200;
	private static  final  int ONETIME_TRANSPERENCY_DECREACING = 7;
	
	
	                            //left, down, right, up
	public static final int[][] KEYS = {{65, 83, 68, 87},
								  {37, 40, 39, 38}};
	
	private static final Point COORD_IF_SPACE_IS_PRESSED = new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);;

	public static final int NUMBER_OF_WALLS = 5;
	
	public static Ship ship1;
	public static Ship ship2;
	public int deltaX;
	public int deltaY;
	
	GamePanel(){
		setLayout(null);
		ship1 = new Ship(new Rectangle(300, 240, DEFAULT_DIAMETER_OF_SHIP, DEFAULT_DIAMETER_OF_SHIP), DEFAULT_SPEED, Color.BLUE, new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)), 255);
		ship2 = new Ship(new Rectangle(250, 450, DEFAULT_DIAMETER_OF_SHIP, DEFAULT_DIAMETER_OF_SHIP),DEFAULT_SPEED, Color.RED, new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)), -20);
		Timer timer = new Timer(DELAY_OF_REFRESHING, this);
		timer.setRepeats(true);
		timer.setActionCommand("timer");
		timer.start();
	}
	
	static Rectangle nowTarget;
	static Rectangle[] walls = new Rectangle[NUMBER_OF_WALLS]; 
	
	boolean theFirstTime = true;
	
	public static boolean firstShipIsDispFirst = true;
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		
		Graphics2D g = (Graphics2D) badG;
		
		if(firstShipIsDispFirst){
			g.setColor(new Color(ship1.getMainColor().getRed(), ship1.getMainColor().getGreen(), ship1.getMainColor().getBlue(), ship1.getTransperency()));
			g.fillArc(ship1.getCoord().x, ship1.getCoord().y, ship1.getDiameter(), ship1.getDiameter(), 0, 360);
			g.setColor(ship1.getSecoundaryColor());
			g.drawArc(ship1.getCoord().x, ship1.getCoord().y, ship1.getDiameter(), ship1.getDiameter(), 0, 360);
			
			g.setColor(new Color(ship2.getMainColor().getRed(), ship2.getMainColor().getGreen(), ship2.getMainColor().getBlue(), ship2.getTransperency()));
			g.fillArc(ship2.getCoord().x, ship2.getCoord().y, ship2.getDiameter(), ship2.getDiameter(), 0, 360);
			g.setColor(ship2.getSecoundaryColor());
			g.drawArc(ship2.getCoord().x, ship2.getCoord().y, ship2.getDiameter(), ship2.getDiameter(), 0, 360);
		}else{
			g.setColor(new Color(ship2.getMainColor().getRed(), ship2.getMainColor().getGreen(), ship2.getMainColor().getBlue(), ship2.getTransperency()));
			g.fillArc(ship2.getCoord().x, ship2.getCoord().y, ship2.getDiameter(), ship2.getDiameter(), 0, 360);
			g.setColor(ship2.getSecoundaryColor());
			g.drawArc(ship2.getCoord().x, ship2.getCoord().y, ship2.getDiameter(), ship2.getDiameter(), 0, 360);
			
			g.setColor(new Color(ship1.getMainColor().getRed(), ship1.getMainColor().getGreen(), ship1.getMainColor().getBlue(), ship1.getTransperency()));
			g.fillArc(ship1.getCoord().x, ship1.getCoord().y, ship1.getDiameter(), ship1.getDiameter(), 0, 360);
			g.setColor(ship1.getSecoundaryColor());
			g.drawArc(ship1.getCoord().x, ship1.getCoord().y, ship1.getDiameter(), ship1.getDiameter(), 0, 360);
		}
			
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(TargetFactory.colorOfTarget);
		if(nowTarget != null)
			g.fillArc(nowTarget.x, nowTarget.y, nowTarget.width, nowTarget.height, 0, 360);
		
		
		g.setFont(new Font("MyFont", Font.PLAIN, 90));
		g.setColor(ship1.getMainColor().brighter());
		g.drawString(String.valueOf(ship1.getLevelOfCoolest()), 10, g.getFontMetrics().getHeight() - 27);
		g.setColor(ship2.getMainColor().brighter());
		g.drawString(String.valueOf(ship2.getLevelOfCoolest()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - g.getFontMetrics().getStringBounds(String.valueOf(ship2.getLevelOfCoolest()), null).getWidth()) - 10, g.getFontMetrics().getHeight() - 27);
		
		/*
		g.setColor(WallFactory.colorOfWall);
		for(Rectangle wall : walls)
			if(wall != null)
				g.fillRoundRect(wall.x, wall.y, wall.width, wall.height, wall.width / 20, wall.height / 20);
		 */
	}
	
	boolean intersected = false;
	boolean wasOutOfScreen1 = false;
	boolean wasOutOfScreen2 = false;
	
	public void actionPerformed(ActionEvent e) {

		//Wall hitting block
		/*
		boolean notInters = true;
		for(Rectangle wall : walls){
			if(wall != null){
				if(ship1.getRect().intersects(wall)){
					notInters = false;
				}
			}
		}
		if(notInters)
		*/
			ship1.setCoord(new Point(ship1.getCoord().x + ship1.getDeltaX(), ship1.getCoord().y + ship1.getDeltaY()));
		/*
		else{
			ship1.setCoord(new Point(ship1.getCoord().x - 8 * ship1.getDeltaX(), ship1.getCoord().y - 8 * ship1.getDeltaY()));
			ship1.setDeltaX(0);
			ship1.setDeltaY(0);
		}
		*/
			
		/*
		notInters = true;
		for(Rectangle wall : walls){
			if(wall != null){
				if(ship2.getRect().intersects(wall)){
					notInters = false;
				}
			}
		}
		if(notInters)
		*/
			ship2.setCoord(new Point(ship2.getCoord().x + ship2.getDeltaX(), ship2.getCoord().y +  ship2.getDeltaY()));
		/*
		else{
			ship2.setCoord(new Point(ship2.getCoord().x - 8 * ship2.getDeltaX(), ship2.getCoord().y - 8 * ship2.getDeltaY()));
			ship2.setDeltaX(0);
			ship2.setDeltaY(0);
		}
		*/
		repaint();
		
		//Intersection block
		if(ship1.getRect().intersects(ship2.getRect())){
			if(intersected == false){
				intersected = true;
				ship1.setTransperency(ship1.getTransperency() - ONETIME_TRANSPERENCY_DECREACING);
				ship2.setTransperency(ship2.getTransperency() - ONETIME_TRANSPERENCY_DECREACING);
			}
		}else{
			intersected = false;
		}
		
		
		//Target hitting block
		if(nowTarget != null){
			if(ship1.getRect().intersects(nowTarget)){
				ship1.increaceLevelOfCoolest();
				System.out.println("1 cool:" + ship1.getLevelOfCoolest());
				nowTarget = null;
			}
		}
		if(nowTarget != null){
			if(ship2.getRect().intersects(nowTarget)){
				ship2.increaceLevelOfCoolest();
				System.out.println("2 cool:" + ship2.getLevelOfCoolest());
				nowTarget = null;
			}
		}
		
		
		//Out-of-screen block
		if(!ship1.getRect().intersects(new Rectangle(0, 0, getSize().width, getSize().height))){
			
			if(new Random().nextBoolean())
				TargetFactory.colorOfTarget = ship2.getSecoundaryColor();
			else
				TargetFactory.colorOfTarget = ship2.getSecoundaryColor();
			
			if(!wasOutOfScreen1){
				Random r = new Random();
				ship1.setMainColor(ship1.getSecoundaryColor());
				ship1.setSecoundaryColor(new Color(r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, ship1.getTransperency()));
				wasOutOfScreen1 = true;
			}
		}else{
			wasOutOfScreen1 = false;
		}
		
		if(!ship2.getRect().intersects(new Rectangle(0, 0, getSize().width, getSize().height))){
			
			if(new Random().nextBoolean())
				TargetFactory.colorOfTarget = ship2.getSecoundaryColor();
			else
				TargetFactory.colorOfTarget = ship2.getSecoundaryColor();
			
			if(!wasOutOfScreen2){
				Random r = new Random();
				ship2.setMainColor(ship2.getSecoundaryColor());
				ship2.setSecoundaryColor(new Color(r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, r.nextInt(255 - MIN_BRIGHTNESS_OF_COLOR) + MIN_BRIGHTNESS_OF_COLOR, ship1.getTransperency()));
				wasOutOfScreen2 = true;
			}
		}else{
			wasOutOfScreen2 = false;
		}
	}
	
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode == KEYS[0][0]){
			ship1.setDeltaX(-(int)ship1.getSpeed());
			/*
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() + ship1.getDeltaX()), (int)(ship1.getCoord().getY() + ship1.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship1.getRect().intersects(wall)){
					ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
					ship1.setDeltaX(0);
				}
			}
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
			*/
		}else if(keyCode == KEYS[0][1]){
			ship1.setDeltaY((int)ship1.getSpeed());
			/*
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() + ship1.getDeltaX()), (int)(ship1.getCoord().getY() + ship1.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship1.getRect().intersects(wall)){
					ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
					ship1.setDeltaY(0);
				}
			}
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
			*/
		}else if(keyCode == KEYS[0][2]){
			ship1.setDeltaX((int)ship1.getSpeed());
			/*
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() + ship1.getDeltaX()), (int)(ship1.getCoord().getY() + ship1.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship1.getRect().intersects(wall)){
					ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
					ship1.setDeltaX(0);
				}
			}
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
			*/
		}else if(keyCode == KEYS[0][3]){
			ship1.setDeltaY(-(int)ship1.getSpeed());
			/*
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() + ship1.getDeltaX()), (int)(ship1.getCoord().getY() + ship1.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship1.getRect().intersects(wall)){
					ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
					ship1.setDeltaY(0);
				}
			}
			ship1.setCoord(new Point((int)(ship1.getCoord().getX() - ship1.getDeltaX()), (int)(ship1.getCoord().getY() - ship1.getDeltaY())));
			*/
		}else if(keyCode == KEYS[1][0]){
			ship2.setDeltaX(-(int)ship2.getSpeed());
			/*
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() + ship2.getDeltaX()), (int)(ship2.getCoord().getY() + ship2.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship2.getRect().intersects(wall)){
					ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
					ship2.setDeltaX(0);
				}
			}
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
			*/
		}else if(keyCode == KEYS[1][1]){
			ship2.setDeltaY((int)ship2.getSpeed());
			/*
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() + ship2.getDeltaX()), (int)(ship2.getCoord().getY() + ship2.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship2.getRect().intersects(wall)){
					ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
					ship2.setDeltaY(0);
				}
			}
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
			*/
		}else if(keyCode == KEYS[1][2]){
			ship2.setDeltaX((int)ship2.getSpeed());
			/*
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() + ship2.getDeltaX()), (int)(ship2.getCoord().getY() + ship2.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship2.getRect().intersects(wall)){
					ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
					ship2.setDeltaX(0);
				}
			}
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
			*/
		}else if(keyCode == KEYS[1][3]){
			ship2.setDeltaY(-(int)ship2.getSpeed());
			/*
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() + ship2.getDeltaX()), (int)(ship2.getCoord().getY() + ship2.getDeltaY())));
			for(Rectangle wall : walls){
				if(ship2.getRect().intersects(wall)){
					ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
					ship2.setDeltaY(0);
				}
			}
			ship2.setCoord(new Point((int)(ship2.getCoord().getX() - ship2.getDeltaX()), (int)(ship2.getCoord().getY() - ship2.getDeltaY())));
			*/
		}
		
		//Go back when space is pressed
		if(keyCode == KeyEvent.VK_SPACE){
			ship1.setCoord(new Point(COORD_IF_SPACE_IS_PRESSED.x - ship1.getDiameter(), COORD_IF_SPACE_IS_PRESSED.y));
			ship2.setCoord(new Point(COORD_IF_SPACE_IS_PRESSED.x, COORD_IF_SPACE_IS_PRESSED.y));
			repaint();
		}
		
	}


	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(keyCode == KEYS[0][0])
			//if(ship1.getDeltaX() < 0)
			ship1.setDeltaX(0);
		
		else if(keyCode == KEYS[0][1])
			//if(ship1.getDeltaY() > 0)
			ship1.setDeltaY(0);
		
		else if(keyCode == KEYS[0][2])
			//if(ship1.getDeltaX() > 0)
			ship1.setDeltaX(0);
		
		else if(keyCode == KEYS[0][3])
			//if(ship1.getDeltaY() < 0)
			ship1.setDeltaY(0);
		
		else if(keyCode == KEYS[1][0])
			//if(ship2.getDeltaX() < 0)
			ship2.setDeltaX(0);
		
		else if(keyCode == KEYS[1][1])
			//if(ship2.getDeltaY() > 0)
			ship2.setDeltaY(0);
		
		else if(keyCode == KEYS[1][2])
			//if(ship2.getDeltaX() > 0)
			ship2.setDeltaX(0);
		
		else if(keyCode == KEYS[1][3])
			//if(ship2.getDeltaY() < 0)
			ship2.setDeltaY(0);
	}
	
	public void keyTyped(KeyEvent arg0) {}

	//ThreadFactory run block
	public void run() {
		
		
	}
	
}
