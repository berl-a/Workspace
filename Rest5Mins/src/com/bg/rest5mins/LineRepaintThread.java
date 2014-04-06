package com.bg.rest5mins;

import java.awt.Toolkit;

public class LineRepaintThread extends Thread {

	public static final double GALMING_OF_LINE = MainCycle.BLOCKED_TIME_MILIS * 0.027;

	public static final double PIXELS_PER_MILISECOUND = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / (MainCycle.BLOCKED_TIME_MILIS - GALMING_OF_LINE);

	private static final long SPEED_OF_REFRESHING = 20;
	
	private static final double INCREASING_VALUE = PIXELS_PER_MILISECOUND * SPEED_OF_REFRESHING;
	
	private static final int CYCLES_TO_INCREASE_TIME = (int)(1000 / SPEED_OF_REFRESHING); 
	
	public void run(){

		MainCycle.frame.panel.XOfLine = 0;
		MainCycle.frame.panel.estimatedTimeMilis = MainCycle.BLOCKED_TIME_MILIS - (int)LineRepaintThread.GALMING_OF_LINE;
		int i = 0;
		System.out.println("I = 0");
		while(true){
			
			MainCycle.frame.panel.XOfLine += INCREASING_VALUE;
			MainCycle.frame.panel.repaint();
			if (i == CYCLES_TO_INCREASE_TIME){
				MainCycle.frame.panel.estimatedTimeMilis-=1000;
				i -= CYCLES_TO_INCREASE_TIME;
			}
			
			try {
				Thread.sleep(SPEED_OF_REFRESHING);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			i++;
			MainCycle.frame.panel.repaint();
			System.out.println(i);
		}
	}
}
