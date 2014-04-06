package com.bg.rest5mins;


public class FirstCircleRepaintThread extends Thread{
	
	public static final long SPEED_OF_ROTATING = 45L;
	
	@Override
	public void run(){
		
		MainCycle.frame.panel.startAngleFirst = 34;
			
		while(true){
		
			MainCycle.frame.panel.startAngleFirst ++;
			MainCycle.frame.panel.angleDelay = 1;
			MainCycle.frame.panel.repaint();
	
			try {
				Thread.sleep(SPEED_OF_ROTATING);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}

}
