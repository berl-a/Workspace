package com.bg.arkanoid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.bg.arkanoid.etypes.ETypeOfBonus;
import com.bg.arkanoid.images.Images;

public class Bonus {

	private static final Dimension DEFAULT_SIZE_OF_BONUS = new Dimension(60, 20);
	
	private static final Point DEFAULT_COORD_OF_BONUS = new Point(10, 10);
	
	private ETypeOfBonus typeOfBonus;
	
	private Dimension sizeOfBonus = DEFAULT_SIZE_OF_BONUS;
	
	private Point coordOfBonus = DEFAULT_COORD_OF_BONUS;
	
	private Images imageLoader;
	
	private BufferedImage imageOfBonus;
	
	public Bonus(ETypeOfBonus type, Brick brick){
		typeOfBonus = type;
		coordOfBonus = createCoordForBonus(brick);
		
		imageLoader = ArcanoidPanel.imageLoader;
		setImageOfBonus(imageLoader.getImage(typeOfBonus.toString()));
	}



	private Point createCoordForBonus(Brick brick) {
		int x = 0, y = 0;
		if(brick.getSize().getWidth() > sizeOfBonus.getWidth())
			x = (int)brick.getCoord().getX() + (int)( brick.getSize().getWidth() - sizeOfBonus.getWidth() ) / 2;
		else if(brick.getSize().getWidth() > sizeOfBonus.getWidth())
			x = (int)brick.getCoord().getX() + (int)( sizeOfBonus.getWidth() - brick.getSize().getWidth() ) / 2;
		else
			x = (int)brick.getCoord().getX();
		
		if(brick.getSize().getHeight() > sizeOfBonus.getWidth())
			y = (int)brick.getCoord().getY() + (int)( brick.getSize().getHeight() - sizeOfBonus.getHeight() ) / 2;
		else if(brick.getSize().getHeight() > sizeOfBonus.getHeight())
			y = (int)brick.getCoord().getY() + (int)( sizeOfBonus.getHeight() - brick.getSize().getHeight() ) / 2;
		else
			y = (int)brick.getCoord().getY();
		return new Point(x, y);
	}

	
	
	public ETypeOfBonus getTypeOfBonus() {
		return typeOfBonus;
	}

	public void setTypeOfBonus(ETypeOfBonus typeOfBonus) {
		this.typeOfBonus = typeOfBonus;
	}



	public Dimension getSizeOfBonus() {
		return sizeOfBonus;
	}



	public void setSizeOfBonus(Dimension sizeOfBonus) {
		this.sizeOfBonus = sizeOfBonus;
	}



	public Point getCoordOfBonus() {
		return coordOfBonus;
	}



	public void setCoordOfBonus(Point coordOfBonus) {
		this.coordOfBonus = coordOfBonus;
	}



	public BufferedImage getImageOfBonus() {
		return imageOfBonus;
	}



	public void setImageOfBonus(BufferedImage imageOfBonus) {
		this.imageOfBonus = imageOfBonus;
	}
}
