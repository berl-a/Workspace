package com.bg.my_threads;


public class Main {
	
	public static void main(String[] args){
		
		MacarenaThread macarenathread = new MacarenaThread("Macarena thr");
		BomBomThread bombomthread = new BomBomThread();
		PalegriaThread palegriathread = new PalegriaThread();
		MusicThread musicthread = new MusicThread();
		
		macarenathread.start();
		bombomthread.start();
		palegriathread.start();
		musicthread.start();
		
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		macarenathread.stopTexting();
		bombomthread.stop();
		palegriathread.stop();
		
		System.out.println();
		System.out.println("EEEEEE" + System.lineSeparator() + "Macarena");
		
	}

}
