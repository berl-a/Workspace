package com.bg.rest5mins;


public class SecoundCircleRepaintThread extends Thread{
	
	public static final long SPEED_OF_ROTATING = 55L;
	
	@Override
	public void run(){
		
		MainCycle.frame.panel.startAngleSecound = 84;
			
		while(true){
			
			MainCycle.frame.panel.startAngleSecound ++;
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
