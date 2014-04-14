package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Ship {

	private static final double SPEED_REMAINDER_DELAY = 0.1;

	private static final double DIAMETER_REMAINDER_DELAY = 0.2;

	private int speed;
	
	private Color mainColor;
	private Color secoundaryColor;
	private int   transperency;
	private Point coord = new Point();
	private int   diameter;
	private int   level_of_coolest = 0;
	private int   deltaX = 0;
	private int   deltaY = 0;

	private double doubleSpeedRemainder;
	
	
	Ship(Rectangle coordAndSize, int speed, Color mainColor, Color secoundaryColor, int transperency){
		
		coord.x = coordAndSize.x;
		coord.y = coordAndSize.y;
		diameter = coordAndSize.width;
		this.speed = speed;
		
		this.setMainColor(mainColor);
		this.setSecoundaryColor(secoundaryColor);
		setTransperency(transperency);
	}
	
	public Rectangle getRect(){
		return new Rectangle(coord.x, coord.y, diameter, diameter);
	}

	public Color getMainColor() {
		return mainColor;
	}

	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}

	public Color getSecoundaryColor() {
		return secoundaryColor;
	}

	public void setSecoundaryColor(Color secoundaryColor) {
		this.secoundaryColor = secoundaryColor;
	}

	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	public int getDiameter(){
		return diameter;
	}
	
	public void setDiameter(int diameter){
		this.diameter = diameter;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public int getTransperency() {
		return transperency;
	}

	public void setTransperency(int transperency) {
		if(!( (transperency < 0) || (transperency > 255) ))
			this.transperency = transperency;
		else
			this.transperency = 255;
	}

	public int getLevelOfCoolest() {
		return level_of_coolest;
	}
	
	public void increaceLevelOfCoolest() {
		
		level_of_coolest++;
		//TODO make normal resize script
		if(level_of_coolest / (1 / DIAMETER_REMAINDER_DELAY) >= 1){
			
			int old_d = diameter;
			diameter = GamePanel.DEFAULT_DIAMETER_OF_SHIP * (int)(level_of_coolest / (1 / DIAMETER_REMAINDER_DELAY) + 1);
			int new_d = diameter;
			setCoord(new Point( getCoord().x + (new_d - old_d), getCoord().y + (new_d - old_d) ));
		}
		
		doubleSpeedRemainder += SPEED_REMAINDER_DELAY;
		setSpeed((int)(getSpeed() + doubleSpeedRemainder));
		if(doubleSpeedRemainder >= 1)
			doubleSpeedRemainder -= 1;
		
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
