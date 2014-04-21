package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class WallFactory extends Thread {

	private static final int SAFE_TIME_TO_CREATE_A_WALL = 1000;
	private static final int FREE_TIME_TO_CREATE_A_WALL = 500;

	private static  final  int DEFAULT_LENGTH_OF_WALL = 500;
	private static  final  int DELAY_OF_LENGTH_OF_WALL = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - DEFAULT_LENGTH_OF_WALL;
	
	private static  final  int DEFAULT_WIDTH_OF_WALL = 20;
	private static  final  int DELAY_OF_WIDTH_OF_WALL = 50;

	public static Color colorOfWall = Color.GRAY;

	public void run(){
		
		while(true){
			
			Random r = new Random();
			int length = r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL;
			int width = r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL;
			
			Rectangle tempRect;
			if(r.nextInt(10) >= 8){
				//Create horizontal
				tempRect = new Rectangle(r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL),    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL), length, width);
			}else{
				//Create vercical
				tempRect = new Rectangle(r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL),    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL), width, length);
			}
			
			if(GamePanel.nowTarget != null){
				while(!(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()).contains(tempRect)) || (tempRect.intersects(GamePanel.ship1.getRect())) || (tempRect.intersects(GamePanel.ship2.getRect())) || tempRect.intersects(GamePanel.nowTarget)){
					length = r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL;
					width = r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL;
					
					if(r.nextInt(10) >= 8){
						//Create horizontal
						tempRect = new Rectangle(    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL)    ,    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL)    ,    r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL    ,    r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL);
					}else{
						//Create vercical
						tempRect = new Rectangle(    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL)    ,    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL)    ,    r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL    ,    r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL);
					}
				}
			}
			else{ //ßêùî ö³ë³ çàðàç íåìàº
				while(!(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()).contains(tempRect)) || (tempRect.intersects(GamePanel.ship1.getRect())) || (tempRect.intersects(GamePanel.ship2.getRect()))){
					length = r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL;
					width = r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL;
					
					if(r.nextInt(10) >= 8){
						//Create horizontal
						tempRect = new Rectangle(    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL)    ,    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL)    ,    r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL    ,    r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL);
					}else{
						//Create vercical
						tempRect = new Rectangle(    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - (DEFAULT_WIDTH_OF_WALL + DELAY_OF_WIDTH_OF_WALL)    ,    r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_LENGTH_OF_WALL + DELAY_OF_LENGTH_OF_WALL)    ,    r.nextInt(DELAY_OF_WIDTH_OF_WALL) + DEFAULT_WIDTH_OF_WALL    ,    r.nextInt(DELAY_OF_LENGTH_OF_WALL) + DEFAULT_LENGTH_OF_WALL);
					}
				}
			}
			System.out.println("New wall");
			GamePanel.walls[r.nextInt(GamePanel.NUMBER_OF_WALLS)] = tempRect;
			MainGameCycle.gp.repaint();
			try {
				Thread.sleep(r.nextInt(FREE_TIME_TO_CREATE_A_WALL) + SAFE_TIME_TO_CREATE_A_WALL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
