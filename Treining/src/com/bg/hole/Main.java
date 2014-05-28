package com.bg.hole;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

	public static HolePanel panel = new HolePanel();
	public static JFrame frame = new JFrame();
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setAlwaysOnTop(true);
		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setContentPane(panel);
		
		frame.addKeyListener(new KeyList());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}







