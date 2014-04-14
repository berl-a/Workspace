package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

public class TargetFactory extends Thread {

	private static final int SAFE_TIME_TO_CREATE_A_TARGET = 8000;
	private static final int FREE_TIME_TO_CREATE_A_TARGET = 5000;

	private static  final  int DEFAULT_DIAMETER_OF_TARGET = 100;
	private static  final  int DELAY_OF_DIAMETER_OF_TARGET = 300;
	public static Color colorOfTarget = Color.RED;
	
	public void run(){
		
		while(true){
			
			Random r = new Random();
			int diameter = r.nextInt(DELAY_OF_DIAMETER_OF_TARGET) + DEFAULT_DIAMETER_OF_TARGET;
			Rectangle tempRect = new Rectangle(r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_DIAMETER_OF_TARGET + DELAY_OF_DIAMETER_OF_TARGET), r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_DIAMETER_OF_TARGET + DELAY_OF_DIAMETER_OF_TARGET), diameter, diameter);
			while(!(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()).contains(tempRect)) || (tempRect.intersects(GamePanel.ship1.getRect())) || (tempRect.intersects(GamePanel.ship2.getRect()))){
				tempRect = new Rectangle(r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_DIAMETER_OF_TARGET + DELAY_OF_DIAMETER_OF_TARGET), r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - (DEFAULT_DIAMETER_OF_TARGET + DELAY_OF_DIAMETER_OF_TARGET), diameter, diameter);
			}
			GamePanel.nowTarget = tempRect;
			GamePanel.firstShipIsDispFirst = !GamePanel.firstShipIsDispFirst;
			
			try {
				Thread.sleep(r.nextInt(FREE_TIME_TO_CREATE_A_TARGET) + SAFE_TIME_TO_CREATE_A_TARGET);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
