package com.bg.my_threads;

public class BomBomThread extends Thread{
	
	public void run(){
		
		while(true){
			
			System.out.println("Bom");
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
