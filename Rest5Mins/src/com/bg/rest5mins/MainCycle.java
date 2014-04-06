package com.bg.rest5mins;

public class MainCycle {
	
	public  static final int UNBLOCKED_TIME_MILIS = 60000 * 25;
	public  static final int BLOCKED_TIME_MILIS   = 60000 * 5;
	
	public static StopFrame frame = new StopFrame();
	
	public static void main(String[] args){
		
		threadSleep(UNBLOCKED_TIME_MILIS);
		
		frame.block();

		threadSleep(BLOCKED_TIME_MILIS);
		
		frame.unblock();

		threadSleep(UNBLOCKED_TIME_MILIS);		
		
		while(StopFrame.goCycle){
			
			frame.block();

			threadSleep(BLOCKED_TIME_MILIS);
			
			frame.unblock();

			threadSleep(UNBLOCKED_TIME_MILIS);
		}
		
		
	}

	private static void threadSleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	


}
