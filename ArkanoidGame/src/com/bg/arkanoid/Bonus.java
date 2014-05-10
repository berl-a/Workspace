package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.Authenticator.RequestorType;

import com.bg.arkanoid.etypes.ETypeOfBonus;
import com.bg.arkanoid.loaders.ImageLoader;

public class Bonus {

	private static final Dimension DEFAULT_SIZE_OF_BONUS = new Dimension(60, 20);
	
	private static final Point DEFAULT_COORD_OF_BONUS = new Point(10, 10);

	private static final double DEFAULT_DELTA_X = 0;

	private static final double DEFAULT_DELTA_Y = 1.5;

	public static final Color COLOR_OF_SUPER_BALL = Color.RED;

	private static final double KOEF_OF_INC_BAT_WIDTH = 1.2;

	private static final double KOEF_OF_BALL_SPEED_INC = 1.2;

	private static final double KOEF_OF_BALL_DIAM_INC = 1.5;

	private static final double INC_SPEED_IF_ROCKET = 3;
	
	private ETypeOfBonus typeOfBonus;
	
	private Dimension sizeOfBonus = DEFAULT_SIZE_OF_BONUS;
	
	private Point coordOfBonus = DEFAULT_COORD_OF_BONUS;
	
	private double deltaX = DEFAULT_DELTA_X;
	private double deltaY = DEFAULT_DELTA_Y; 
	
	private ImageLoader imageLoader;
	
	private BufferedImage imageOfBonus;
	
	public Bonus(ETypeOfBonus type, Brick brick){
		setType(type);
		coordOfBonus = createCoordForBonus(brick);
		
		imageLoader = ArcanoidPanel.imageLoader;
		setImage(imageLoader.getImage(typeOfBonus.toString()));
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
	
	public ETypeOfBonus getType() {
		return typeOfBonus;
	}

	public void setType(ETypeOfBonus typeOfBonus) {
		this.typeOfBonus = typeOfBonus;
		
		if(typeOfBonus == ETypeOfBonus.DESTROYING_ROCKET){
			setDeltaY(getDeltaY() * INC_SPEED_IF_ROCKET);
			System.out.println(getDeltaY() + " " + getType().toString());
		}
	}


	public Dimension getSize() {
		return sizeOfBonus;
	}

	public void setSize(Dimension sizeOfBonus) {
		this.sizeOfBonus = sizeOfBonus;
	}

	public Point getCoord() {
		return coordOfBonus;
	}

	public void setCoord(Point coordOfBonus) {
		this.coordOfBonus = coordOfBonus;
	}

	public BufferedImage getImage() {
		//setSize(new Dimension((int)imageOfBonus.getWidth(), (int)imageOfBonus.getHeight()));
		return imageOfBonus;
	}
	
	public void setImage(BufferedImage imageOfBonus) {
		this.imageOfBonus = imageOfBonus;
		setSize(new Dimension((int)imageOfBonus.getWidth(), (int)imageOfBonus.getHeight()));
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
	
	public static boolean backCoordWhenBallFalls = false;
	public static Point oldCoordOfBat = null;
	
	public void getEffect(){
		
		if(getType() == ETypeOfBonus.SUPER_BALL){
			
			ArcanoidPanel.ball.superBall = true;
			ArcanoidPanel.ball.setColor(Ball.SUPER_BALL_COLOR);
			
		}else if(getType() == ETypeOfBonus.EXTRA_LIFE){
			
			ArcanoidPanel.player.setLifes(ArcanoidPanel.player.getLifes() + 1);
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.EXTRA_LIFE.toString());
			
		}else if(getType() == ETypeOfBonus.INC_BAT_WIDTH){
			
			ArcanoidPanel.bat.setSize(new Dimension((int)(ArcanoidPanel.bat.getSize().getWidth() * KOEF_OF_INC_BAT_WIDTH), (int)ArcanoidPanel.bat.getSize().getHeight()));
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.INC_BAT_WIDTH.toString());
			
		}else if(getType() == ETypeOfBonus.DEC_BAT_WIDTH){
			
			ArcanoidPanel.bat.setSize(new Dimension((int)(ArcanoidPanel.bat.getSize().getWidth() / KOEF_OF_INC_BAT_WIDTH), (int)ArcanoidPanel.bat.getSize().getHeight()));
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.DEC_BAT_WIDTH.toString());
			
		}else if(getType() == ETypeOfBonus.INC_BALL_SPEED){
	
			ArcanoidPanel.ball.setDeltaX(ArcanoidPanel.ball.getDeltaX() * KOEF_OF_BALL_SPEED_INC);
			ArcanoidPanel.ball.setDeltaY(ArcanoidPanel.ball.getDeltaY() * KOEF_OF_BALL_SPEED_INC);
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.INC_BALL_SPEED.toString());
	
		}else if(getType() == ETypeOfBonus.DEC_BALL_SPEED){
		
			ArcanoidPanel.ball.setDeltaX(ArcanoidPanel.ball.getDeltaX() / KOEF_OF_BALL_SPEED_INC);
			ArcanoidPanel.ball.setDeltaY(ArcanoidPanel.ball.getDeltaY() / KOEF_OF_BALL_SPEED_INC);
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.DEC_BALL_SPEED.toString());
		
		}else if(getType() == ETypeOfBonus.INC_BALL_SIZE){
			
			ArcanoidPanel.ball.setDiameter(ArcanoidPanel.ball.getDiameter() * KOEF_OF_BALL_DIAM_INC);
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.INC_BALL_SIZE.toString());
		
		}else if(getType() == ETypeOfBonus.DEC_BALL_SIZE){
			
			ArcanoidPanel.ball.setDiameter(ArcanoidPanel.ball.getDiameter() / KOEF_OF_BALL_DIAM_INC);
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.DEC_BALL_SIZE.toString());
			
		}else if(getType() == ETypeOfBonus.DESTROYING_ROCKET){
			
			backCoordWhenBallFalls = true;
			oldCoordOfBat = ArcanoidPanel.bat.getCoord();
			ArcanoidPanel.bat.setCoord(new Point((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.DESTROYING_ROCKET.toString());
			
		}else if(getType() == ETypeOfBonus.USUAL_BALL){
			ArcanoidPanel.ball.superBall = false;
			ArcanoidPanel.ball.setColor(Ball.DEFAULT_COLOR);
			
			ArcanoidPanel.ball.setDiameter(Ball.DEFAULT_DIAMETER);
			
			ArcanoidPanel.soundLoader.playSound(ETypeOfBonus.USUAL_BALL.toString());
		}
		
		ArcanoidPanel.removeBonus(this);
	}
	
	
	public static void looseEffect(){
		
		ArcanoidPanel.ball.superBall = false;
		ArcanoidPanel.ball.setColor(Ball.DEFAULT_COLOR);
		
		ArcanoidPanel.bat.setSize(ArcanoidPanel.bat.getNormalSize());
		
		ArcanoidPanel.ball.setDeltaX(Ball.DEFAULT_DELTA_X);
		ArcanoidPanel.ball.setDeltaY( - Ball.DEFAULT_DELTA_Y);
		
		ArcanoidPanel.ball.setDiameter(Ball.DEFAULT_DIAMETER);
	}
	
	
}
