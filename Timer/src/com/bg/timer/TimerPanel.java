package com.bg.timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;



public class TimerPanel extends JPanel implements ActionListener, MouseMotionListener, CaretListener{

	private static final int DELAY_OF_TIMER = 20;
	JTextField fieldMin = new JTextField(2);
	JTextField fieldSec = new JTextField(2);
	
	private double timeFull = 0L;
	private double timeElapsed = 0L;
	
	Timer timer = new Timer(DELAY_OF_TIMER, this);
	private boolean started;
	
	TimerPanel(){
		setBackground(new Color(0,0,0,0));
		
		Font bigFont = new Font("font", Font.BOLD, 40);
		
		fieldMin.setBackground(new Color(200, 10, 10, 255));
		fieldMin.setFont(bigFont);
		fieldMin.setForeground(Color.BLUE);
		fieldMin.setSelectedTextColor(Color.RED);
		fieldMin.setCaretColor(fieldMin.getBackground());
		fieldMin.setBorder(null);
		fieldMin.addCaretListener(this);
		fieldMin.setHorizontalAlignment(JTextField.CENTER);
		
		fieldSec.setBackground(new Color(200, 10, 10, 255));
		fieldSec.setFont(bigFont);
		fieldSec.setForeground(Color.BLUE);
		fieldSec.setSelectedTextColor(Color.RED);
		fieldSec.setCaretColor(fieldSec.getBackground());
		fieldSec.setBorder(null);
		fieldSec.addCaretListener(this);
		fieldSec.setHorizontalAlignment(JTextField.CENTER);
		
		add(fieldMin);
		add(fieldSec);
		
		setOpaque(false);
	}
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.RED);
		if(started)
			g.fillRect(0, 0, getWidth(), (int)(getHeight() * (1 - timeElapsed / timeFull)));
		
	}

	private void start(double time){
		started = true;
		timeFull = time;
		timeElapsed = time;
		fieldMin.setVisible(false);
		fieldSec.setVisible(false);
		repaint();
		
		timer.start();
	}
	
	private void finish() {
		playSound("beep.wav");
	}
	
	private boolean checkNumber(String text) {
		try{
			Integer.parseInt(text);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		timeElapsed -= DELAY_OF_TIMER;
		repaint();		
		if(timeElapsed <= 0)
			finish();
	}
	
	
	public void mouseDragged(MouseEvent e) {
		if(checkNumber(fieldMin.getText()) && checkNumber(fieldMin.getText()))
			start(( Integer.parseInt(fieldMin.getText()) * 60 +  Integer.parseInt(fieldSec.getText()) ) * 1000);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void caretUpdate(CaretEvent arg0) {
		fieldMin.repaint();
		fieldSec.repaint();
	}
	
	private void playSound(final String s) {
		new Thread(){
			public void run(){
				File soundFile = null;
				soundFile = new File(s);
			    AudioInputStream audioStream;
			    Clip clip;
			    
				try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
					clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.setFramePosition(0);
				    clip.start();
				    Thread.sleep(clip.getMicrosecondLength()/1000);
				    clip.stop();
				    clip.close();
				    stop();
				    
				} catch (Exception e) {}
			}
		}.start();
		
		
	}
	
}
