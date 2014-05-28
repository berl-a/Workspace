package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Rectangle;

public class SavingLine {

	private Color color;
	private Rectangle rect;
	
	public SavingLine(Color c, Rectangle rect){
		this.color = c;
		this.rect = rect;
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setHeight(Rectangle rect) {
		this.rect = rect;
	}
	
	
}
