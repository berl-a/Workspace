package com.bg.my_threads;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicThread extends Thread {
	
	public void run(){
		
		File soundFile = new File("res/Linkin_Park_-_Numb.wav"); //�������� ����
	    
	    //�������� AudioInputStream
	    //��� ��� ����� �������� IOException � UnsupportedAudioFileException
	    AudioInputStream audioStream;
	    Clip clip;
		try {
			audioStream = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.setFramePosition(0); //������������� ��������� �� �����
		    clip.start(); //�������!!!
		    Thread.sleep(clip.getMicrosecondLength()/1000); //���� ����� �����
		    clip.stop(); //�������������
		    clip.close(); //���������
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
			    
	    //�������� ���������� ���������� Clip
	    //����� �������� LineUnavailableException

	    
	    //��������� ��� �������� ����� � Clip
	    //����� �������� IOException � LineUnavailableException

	    
	    
	}

}
