package com.bg.my_threads;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AnotherMusicThread extends Thread{
	
	public void run(){
		
		File audioFile = new File("res/Linkin_Park_-_Numb.wav");
		
		AudioInputStream audioStream;
		
		Clip clip;
		try{
			audioStream = AudioSystem.getAudioInputStream(audioFile);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.setFramePosition(0);
			clip.start();
			sleep(clip.getMicrosecondLength()/1000);
			clip.stop();
			clip.close();
		}catch(Exception e){
			System.out.println("Error while playing music file");
		}
	}
		

}
