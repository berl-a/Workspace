package com.bg.arkanoid;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainInArcanoid {

	public static JFrame frame;
	public static ArcanoidPanel panel;
	
	public static void main(String[] args){
		frame = new JFrame();
		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setUndecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		startGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void startGame() {
		panel = new ArcanoidPanel();
		frame.setContentPane(panel);
		frame.addKeyListener(panel);
		frame.setVisible(true);
	}

}
