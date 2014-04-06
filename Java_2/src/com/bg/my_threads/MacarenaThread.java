package com.bg.my_threads;

public class MacarenaThread extends Thread{

	private boolean running = true;

	public MacarenaThread(String string) {
		super(string);
	}

	public void run(){

		while(running){
			
			System.out.println("Lalala");
			try {
				sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopTexting(){
		running = false;
	}
}
