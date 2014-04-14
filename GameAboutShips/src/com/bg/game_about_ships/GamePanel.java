package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
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
	private static  final  int ONETIME_TRANSPERENCY_DECREACING = 5;
	
	                            //left, down, right, up
	public static final int[][] KEYS = {{65, 83, 68, 87},
								  {37, 40, 39, 38}};

	public static Ship ship1;
	public static Ship ship2;
	public int deltaX;
	public int deltaY;
	
	GamePanel(){
		
		setLayout(null);
		ship1 = new Ship(new Rectangle(300, 240, DEFAULT_DIAMETER_OF_SHIP, DEFAULT_DIAMETER_OF_SHIP), DEFAULT_SPEED, Color.BLUE, Color.CYAN, 255);
		ship2 = new Ship(new Rectangle(250, 450, DEFAULT_DIAMETER_OF_SHIP, DEFAULT_DIAMETER_OF_SHIP),DEFAULT_SPEED, Color.RED, Color.YELLOW, -20);
		Timer timer = new Timer(DELAY_OF_REFRESHING, this);
		timer.setRepeats(true);
		timer.setActionCommand("timer");
		timer.start();
	}
	
	static Rectangle nowTarget;
	
	boolean theFirstTime = true;
	
	public static boolean firstShipIsDispFirst = true;
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
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
			
		g.setColor(TargetFactory.colorOfTarget);
		if(nowTarget != null)
			g.fillArc(nowTarget.x, nowTarget.y, nowTarget.width, nowTarget.height, 0, 360);
		
			
	}
	
	boolean intersected = false;
	boolean wasOutOfScreen1 = false;
	boolean wasOutOfScreen2 = false;
	
	public void actionPerformed(ActionEvent e) {

		ship1.setCoord(new Point(ship1.getCoord().x + ship1.getDeltaX(), ship1.getCoord().y + ship1.getDeltaY()));
		ship2.setCoord(new Point(ship2.getCoord().x + ship2.getDeltaX(), ship2.getCoord().y +  ship2.getDeltaY()));
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
		
		if(keyCode == KEYS[0][0])
			ship1.setDeltaX(-(int)ship1.getSpeed());
		else if(keyCode == KEYS[0][1])
			ship1.setDeltaY((int)ship1.getSpeed());
		else if(keyCode == KEYS[0][2])
			ship1.setDeltaX((int)ship1.getSpeed());
		else if(keyCode == KEYS[0][3])
			ship1.setDeltaY(-(int)ship1.getSpeed());
		else if(keyCode == KEYS[1][0])
			ship2.setDeltaX(-(int)ship2.getSpeed());
		else if(keyCode == KEYS[1][1])
			ship2.setDeltaY((int)ship2.getSpeed());
		else if(keyCode == KEYS[1][2])
			ship2.setDeltaX((int)ship2.getSpeed());
		else if(keyCode == KEYS[1][3])
			ship2.setDeltaY(-(int)ship2.getSpeed());
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
