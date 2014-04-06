package com.bg.mouse_gun;

import java.awt.Robot;
import java.awt.event.MouseEvent;

public class Gun {

	public long shootDelay = 0;
	public long numberOfShoots = 0;
	public static boolean playTheLastMusic;

	public void playTicks(final int timeToPlay, final int tickDelay){
		
		
		Thread waitThread = new Thread(){
			public void run(){
				          //ўоб пот≥м вв≥мкнути останн≥й звук
				for(int i = 1; i < (timeToPlay / tickDelay); i++){
					MusicThread mThr = new MusicThread();
					mThr.start();
					try {
						mThr.join();
					} catch (InterruptedException e) {}
					
					mThr.stop();
				}
				Gun.playTheLastMusic = true;
				MusicThread mThr = new MusicThread();
				mThr.start();
				
				try {
					mThr.join();
				} catch (InterruptedException e) {}
				
				mThr.stop();
				
			}
		};
		waitThread.start();
		
		try {
			waitThread.join();
		} catch (InterruptedException e) {}
		
		waitThread.stop();
		shoot();
	}
	
	
	private void shoot() {
		try{
			for(int i = 0; i < numberOfShoots; i++){
				Robot r = new Robot();
				r.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				r.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(shootDelay);
			}
			
		}catch(Exception e){}
		System.exit(0);
		
	}
}
