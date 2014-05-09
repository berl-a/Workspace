package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Bat {
	
	private Point coord;
	private Rectangle size;
	private Color color;
	private double deltaX;
	
	public Point getCoord() {
		return coord;
	}
	public void setCoord(Point coord) {
		this.coord = coord;
	}
	public Rectangle getSize() {
		return size;
	}
	public void setSize(Rectangle size) {
		this.size = size;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public double getDeltaX() {
		return deltaX;
	}
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	
	
	public Line2D getUp0(){
		return new Line2D.Double(getCoord().getX(), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 5), getCoord().getY());
	}
	
	public Line2D getUp1(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 5), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 5 * 2), getCoord().getY());
	}
	
	public Line2D getUp2(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 5 * 2), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 5 * 3), getCoord().getY());
	}
	public Line2D getUp3(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 5 * 3), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 5 * 4), getCoord().getY());
	}
	public Line2D getUp4(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 5 * 4), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 5 * 5), getCoord().getY());
	}
	
	
	/*
	public Line2D getUp0(){
		return new Line2D.Double(getCoord().getX(), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 3), getCoord().getY());
	}
	
	public Line2D getUp1(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 3), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 3 * 2), getCoord().getY());
	}
	
	public Line2D getUp2(){
		return new Line2D.Double(getCoord().getX() + (getSize().getWidth() / 3 * 2), getCoord().getY(), getCoord().getX() + (getSize().getWidth() / 3 * 3), getCoord().getY());
	}
	*/
	
	public Line2D getLeft(){
		return new Line2D.Double(getCoord().getX(), getCoord().getY(), getCoord().getX(), getCoord().getY() + getSize().getHeight());
	}
	
	public Line2D getRight(){
		return new Line2D.Double(getCoord().getX() + getSize().getWidth(), getCoord().getY(), getCoord().getX() + getSize().getWidth(), getCoord().getY() + getSize().getHeight());
	}
	
}
