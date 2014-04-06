package com.bg.rest5mins;

public class MusicThread extends Thread{
	
	@Override
	public void run(){

		MainCycle.frame.panel.repaint();
	}

}
