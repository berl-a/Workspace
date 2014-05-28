import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class BeepButtonFrame implements ActionListener {
	
	JButton button = new JButton();

	JButton left = new JButton();

	JButton right = new JButton();
	
	ImageIcon normal, pressed;
	
	
	
	public BeepButtonFrame() {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		frame.setLayout(new BorderLayout());
		frame.setAlwaysOnTop(true);
		
		button.addActionListener(this);
		button.setActionCommand("main");
		button.setBackground(new Color(0,0,0,0));
		button.setForeground(new Color(0,0,0,0));
		
		left.addActionListener(this);
		left.setActionCommand("left");
		left.setBackground(Color.RED.brighter().brighter());
		
		right.addActionListener(this);
		right.setActionCommand("right");
		right.setBackground(Color.GREEN.brighter().brighter());
		
		try {
			//normal = new ImageIcon(ImageIO.read(new File("res/normal.png")));
			normal = new ImageIcon(ImageIO.read(getClass().getResource("res/normal.png")));
		} catch (IOException e) {}
		
		try {
			//pressed = new ImageIcon(ImageIO.read(new File("res/pressed.png")));
			pressed = new ImageIcon(ImageIO.read(getClass().getResource("res/pressed.png")));
		} catch (IOException e) {}
		
		button.setIcon(normal);
		
		frame.add(button, BorderLayout.CENTER);
		frame.add(left, BorderLayout.WEST);
		frame.add(right, BorderLayout.EAST);
		
		frame.pack();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - frame.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - frame.getHeight() / 2));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "main"){
			
			playSound("res/clax.wav");
			
			button.setBackground(new Color(0,0,0,0));
			button.repaint();
			/*
			button.setIcon(pressed);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {}
			button.setIcon(normal);
			*/
			
		}else if(e.getActionCommand() == "left"){
			
			playSound("res/tube.wav");
			
		}else if(e.getActionCommand() == "right"){
			
			playSound("res/cheers.wav");
			
		}
		
	}

	private void playSound(final String s) {
		new Thread(){
			public void run(){
				File soundFile = new File(s);
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
				    button.setBackground(new Color(0,0,0,0));
				    button.repaint();
				    stop();
				    
				} catch (Exception e) {}
			}
		}.start();
		
	}
}
