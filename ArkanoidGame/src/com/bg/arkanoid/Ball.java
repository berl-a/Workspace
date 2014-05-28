package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class Ball {

	public static final double DEFAULT_DELTA_X = 2d;
	public static final double DEFAULT_DELTA_Y = - 2d;
	
	public static final double DEFAULT_DIAMETER = 20d;

	private static final Point DEFAULT_COORD = new Point((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - DEFAULT_DIAMETER / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() -  DEFAULT_DIAMETER * 5));

	public static final Color DEFAULT_COLOR = Color.PINK;
	
	public static final Color SUPER_BALL_COLOR = new Color(new Random().nextInt(155) + 100, new Random().nextInt(155) + 100, new Random().nextInt(155) + 100);
	
	public boolean holdNearBat = true;
	
	public boolean isMagnetted = false;
	
	public boolean superBall = false;
	
	public Ball(){
		setCoord(DEFAULT_COORD);
		setDiameter(DEFAULT_DIAMETER);
		setColor(DEFAULT_COLOR);
		setDeltaX(DEFAULT_DELTA_X);
		setDeltaY(DEFAULT_DELTA_Y);
	}
	
	private Point coord;
	
	private double deltaX;
	private double deltaY;
	private double addDeltaX;
	private double addDeltaY;
	
	private double diameter;
	private Color color;
	
	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public double getDiameter() {
		return diameter;
	}
	
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	
	public Rectangle getRect(){
		return new Rectangle((int)getCoord().getX(), (int)getCoord().getY(), (int)getDiameter(), (int)getDiameter());
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	//private Color oldColor;
	public void hit() {
		/*
		oldColor = getColor();
		this.setColor(Color.RED);
		Timer t = new Timer(5, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setColor(oldColor);
			}
		});
		t.setRepeats(false);
		t.start();
		*/
	}


	public double getAddDeltaX() {
		return addDeltaX;
	}

	public void setAddDeltaX(double addDeltaX) {
		this.addDeltaX = addDeltaX;
	}

	public double getAddDeltaY() {
		return addDeltaY;
	}

	public void setAddDeltaY(double addDeltaY) {
		this.addDeltaY = addDeltaY;
	}

}
