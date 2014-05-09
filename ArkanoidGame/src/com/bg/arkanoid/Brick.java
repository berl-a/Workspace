package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.Random;

import com.bg.arkanoid.etypes.ETypeOfBlock;
import com.bg.arkanoid.etypes.ETypeOfBonus;

public class Brick {

	private static final int DEFAULT_HITS_TO_DESTROY = 1;
	private Color color;
	private Dimension size;
	private Point coord;
	private ETypeOfBlock typeOfBlock;
	
	private Bonus bonus;
	
	private int hitsToDestroy = DEFAULT_HITS_TO_DESTROY;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Point getCoord() {
		return coord;
	}

	public void setCoord(Point coord) {
		this.coord = coord;
		if(( getSize() != null ) && (getTypeOfBlock() == ETypeOfBlock.BONUS_BLOCK))
			setBonus();
	}
	
	public Rectangle getRect(){
		return new Rectangle((int)getCoord().getX(), (int)getCoord().getY(), (int)getSize().getWidth(), (int)getSize().getHeight());
	}
	
	public Line2D.Double getUpSide(){
		return new Line2D.Double(getCoord().x, getCoord().y, getCoord().x + getSize().width, getCoord().y);
	}
	
	public Line2D.Double getDownSide(){
		return new Line2D.Double(getCoord().x, getCoord().y + getSize().getHeight(), getCoord().x + getSize().width, getCoord().y + getSize().getHeight());
	}
	
	public Line2D.Double getLeftSide(){
		return new Line2D.Double(getCoord().x, getCoord().y, getCoord().x, getCoord().y + getSize().height);
	}
	public Line2D.Double getRightSide(){
		return new Line2D.Double(getCoord().x + getSize().width, getCoord().y, getCoord().x + getSize().width, getCoord().y + getSize().getHeight());
	}
	
	public static void remove(final Brick b) {
		
		/*
		Runnable removeRunnable = new Runnable(){
			public void run(){
				
				b.setHitsToDestroy(b.getHitsToDestroy() - 1);
				System.out.println(b.getHitsToDestroy());
				MainInArcanoid.panel.repaint();
				if(b.getHitsToDestroy() == 0){
				
					b.setColor(b.getColor().darker());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {}
					
					b.setColor(b.getColor().darker());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {}
					
					ArcanoidPanel.bricks.remove(b);
					MainInArcanoid.panel.repaint();
				}
			}
		};
		Thread removeThread = new Thread(removeRunnable);
		removeThread.start();
		*/
		
		b.setHitsToDestroy(b.getHitsToDestroy() - 1);
		System.out.println(b.getHitsToDestroy());
		MainInArcanoid.panel.repaint();
		
		if(b.getHitsToDestroy() == 0){
			b.setColor(b.getColor().darker());
			b.setColor(b.getColor().darker());
			ArcanoidPanel.bricks.remove(b);
			MainInArcanoid.panel.repaint();
		}
		
		ArcanoidPanel.player.setScores(ArcanoidPanel.player.getScores() + b.getTypeOfBlock().getScoresForDestroying());
		
	}
	

	public ETypeOfBlock getTypeOfBlock() {
		return typeOfBlock;
	}

	public void setTypeOfBlock(ETypeOfBlock typeOfBlock) {
		
		this.typeOfBlock = typeOfBlock;
		setHitsToDestroy(this.typeOfBlock.getHitsToDestroy());
		
	}

	private void setBonus() {
		bonus = new Bonus(ETypeOfBonus.values()[new Random().nextInt(ETypeOfBonus.values().length)], this);
		System.out.println(bonus.getTypeOfBonus().toString());
	}

	public int getHitsToDestroy() {
		return hitsToDestroy;
	}

	public void setHitsToDestroy(int hitsToDestroy) {
		this.hitsToDestroy = hitsToDestroy;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
}
