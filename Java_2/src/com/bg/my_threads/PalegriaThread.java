package com.bg.my_threads;

public class PalegriaThread extends Thread{
	
	public void run(){
		
		while(true){
			
			System.out.println("Palegria Macarena");
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
