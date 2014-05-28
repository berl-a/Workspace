package com.bg.arkanoid;

import java.awt.Toolkit;

import javax.swing.JFrame;

import com.bg.arkanoid.possible_menu.PossibleMenuPanel;

public class MainInArcanoid {

	public static JFrame frame;
	public static ArcanoidPanel gamePanel;
	public static PossibleMenuPanel menuPanel;
	
	public static void main(String[] args){
		frame = new JFrame();
		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setUndecorated(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		startGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void startGame() {
		gamePanel = new ArcanoidPanel();
		
		frame.setContentPane(gamePanel);
		frame.addKeyListener(gamePanel);
		
		//menuPanel = new PossibleMenuPanel();
		//frame.setContentPane(menuPanel);
		//frame.addMouseListener(menuPanel);
		//frame.addMouseMotionListener(menuPanel);
		
		frame.setVisible(true);	}

}
