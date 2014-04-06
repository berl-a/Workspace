package com.bg.mouse_gun;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicThread extends Thread{

	private static final String MUSIC_FILE_URL = "res/tick.wav";
	private static final String SECOUND_MUSIC_FILE_URL = "res/bang.wav";
	
	public void run(){
		
	    AudioInputStream audioStream;
	    Clip clip;
		try {
			if(!Gun.playTheLastMusic)
				audioStream = AudioSystem.getAudioInputStream(getClass().getResource(MUSIC_FILE_URL));
			else
				audioStream = AudioSystem.getAudioInputStream(getClass().getResource(SECOUND_MUSIC_FILE_URL));
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.setFramePosition(0); //устанавливаем указатель на старт
		    clip.start(); //Поехали!!!
		    Thread.sleep(clip.getMicrosecondLength()/1000); //Ждем конца песни
		    clip.stop(); //Останавливаем
		    clip.close(); //Закрываем
		    stop();
		    
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
