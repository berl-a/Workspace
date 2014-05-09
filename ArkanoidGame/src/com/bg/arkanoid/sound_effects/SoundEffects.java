package com.bg.arkanoid.sound_effects;

import java.io.File;
import java.nio.file.Files;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffects {
                                  //Block hit                                      //Bat            //Wall
	public static String[] urls = {"res/hit1.wav", "res/sword.wav", "res/hit3.wav", "res/ball.wav", "res/яся_стіна.wav"};
	public static LinkedList<File> sounds = new LinkedList<File>();
	public static LinkedList<Thread> soundThreads = new LinkedList<Thread>();
	
	public boolean loadSounds(){
		try{
			for(String url : urls){
				sounds.add(new File(url));
				
				//sounds.add(new File(getClass().getResource(url).toURI()));
				
				soundThreads.add(new MusicPlayer(sounds.getLast()));
			}
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public void playSound(int index){
		soundThreads.get(index).start();
		soundThreads.remove(index);
		soundThreads.add(index, new MusicPlayer(sounds.get(index)));
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
				System.out.println(musicFile.toString());
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
