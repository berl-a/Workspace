package com.bg.arkanoid.loaders;

import java.io.File;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.bg.arkanoid.etypes.ETypeOfBonus;

public class SoundLoader {
    
	public static String[] urls = {
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/batInc.wav",
		"res/sounds/batDec.wav",
		"res/sounds/speedInc.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav",
		"res/sounds/superBall.wav"
		
	};
	
	public static String[] names = {
		ETypeOfBonus.SUPER_BALL.toString(),
		ETypeOfBonus.EXTRA_LIFE.toString(),
		ETypeOfBonus.INC_BAT_WIDTH.toString(),
		ETypeOfBonus.DEC_BAT_WIDTH.toString(),
		ETypeOfBonus.INC_BALL_SPEED.toString(),
		ETypeOfBonus.DEC_BALL_SPEED.toString(),
		ETypeOfBonus.INC_BALL_SIZE.toString(),
		ETypeOfBonus.DEC_BALL_SIZE.toString(),
		ETypeOfBonus.DESTROYING_ROCKET.toString(),
		ETypeOfBonus.USUAL_BALL.toString(),
		ETypeOfBonus.SAVING_LINE.toString(),
		ETypeOfBonus.MAGNET_BAT.toString()
	};
	
	public static LinkedHashMap<String, File> sounds = new LinkedHashMap<String, File>();
	public static LinkedHashMap<String, Thread> soundThreads = new LinkedHashMap<String, Thread>();
	
	public boolean loadSounds(){
		try{
			for(int i = 0; i < urls.length; i++){
				
				//sounds.put(names[i], new File(urls[i]));
				sounds.put(names[i], new File(getClass().getResource(urls[i]).toURI()));
				
				soundThreads.put(names[i], new MusicPlayer(sounds.get(names[i])));
			}
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public void playSound(String name){
		soundThreads.get(name).start();
		soundThreads.remove(name);
		soundThreads.put(name, new MusicPlayer(sounds.get(name)));
	}
	
	private class MusicPlayer extends Thread{
		
		AudioInputStream audioStream;
	    Clip clip;
		
		private File musicFile;
		
		MusicPlayer(File file){
			this.musicFile = file;
		}
		
		public void run(){
			try {
				audioStream = AudioSystem.getAudioInputStream(musicFile);
				clip = AudioSystem.getClip();
				clip.open(audioStream);
				clip.setFramePosition(0); //устанавливаем указатель на старт
			    clip.start(); //Поехали!!!
			    Thread.sleep(clip.getMicrosecondLength()/1000); //Ждем конца песни
			    clip.stop(); //Останавливаем
			    clip.close(); //Закрываем
			    stop();
			} catch (Exception e) {}
		}
		
	}
	
}
